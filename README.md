# clojure-agent-demo
Code for a screencast demonstrating the use of agents in Clojure to parallelize a set of logically independent calculations.

The example I'm going to use is counting occurrences of words in a largish text document. The basic process will be:

 1. Read in the file
 1. Convert everything to lowercase (so we don't have to worry about matching case)
 1. Split the text into words
 1. Create a set of all the unique words
 1. For each unique word, create an agent that will have the responsibility of counting occurrences of that word
 1. Create a map that maps unique words to their agents, which we can use to look up the different word counts.

Then to see if it actually _did_ something, we'll sum up all the final counts and see if that matches the total number of words.

This probably isn't a terribly clever way to do this, especially since we have to create the set of unique words before we can start, but it will hopefully illustrate the point.

Also note that our splitting of the text into words isn't terribly good and will do things like turn "Sally's" into two words, "Sally" and "s". We could do a better job of handling punctuation, but I didn't want to get too distracted.

<!--
## Installation

Download from http://example.com/FIXME.

## Usage

FIXME: explanation

    $ java -jar clojure-agent-atom-demo-0.1.0-standalone.jar [args]

## Options

FIXME: listing of options this app accepts.

## Examples

...

### Bugs

...

### Any Other Sections
### That You Think
### Might be Useful

-->

## License

Copyright © 2015 Nic McPhee

Distributed under the MIT License.