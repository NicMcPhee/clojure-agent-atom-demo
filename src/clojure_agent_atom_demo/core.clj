(ns clojure-agent-atom-demo.core
  (:gen-class))

(defn count-word
  [_ word word-list]
  (count (filter (partial = word) word-list)))

(defn make-count-map
  [word-set word-list]
  (let [count-map (into {}
                        (for [w word-set]
                          [w (agent nil)]))]
    (doseq [w word-set]
      (send (count-map w) count-word w word-list))
    count-map))

(defn get-count
  [word count-map]
  (when-not (deref (count-map word))
    (await (count-map word)))
  (deref (count-map word)))

(defn -main
  "Demonstrate counting the occurrences of unique words in a text"
  [& args]
  (let [text (slurp (first args))
        lower-case-text (clojure.string/lower-case text)
        words (re-seq #"\w+" lower-case-text)
        word-set (set words)
        count-map (make-count-map word-set words)
        total-count (reduce + (map #(get-count % count-map) word-set))]
    (println (str "There were " total-count " words in the count map."))
    (println (str "There were " (count words) " words in the word list."))
    (shutdown-agents)))
