(ns clojure-agent-atom-demo.core
  (:gen-class))

(defn count-word
  [word word-list]
  (count (filter (partial = word) word-list)))

(defn make-count-map
  [word-set word-list]
  (into {}
        (for [w word-set]
          [w (count-word w word-list)])))

(defn get-count
  [word count-map]
  (count-map word))

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
