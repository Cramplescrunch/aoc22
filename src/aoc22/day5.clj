(ns aoc22.day5
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str]))

(def test-input "    [D]
[N] [C]
[Z] [M] [P]
 1   2   3

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2")

(def test-input2 {:stacks '((\N \Z)
                            (\D \C \M)
                            (\P))
                  :instructions "move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to "})

(:stacks test-input2)

(println (get test-input2 :instructions))

(def real-input (slurp (io/resource "day5.txt")))

(defn update-stacks
  "Takes a vector of three stacks and a vector of three numbers representing the instructions.
  Returns a vector of the three updated stacks."
  [stacks instructions]
  )

(pop '(1 2 3))

(list* (peek '(1 2 3)) '[2 3])

(defn move-top-crate
  [start-stack end-stack]
  (list* (peek start-stack) end-stack))

(move-top-crate '(\D \C \M) '(\N \Z))
(move-top-crate (:stack test-input2))

(peek '(\D \C \M))

(defn get-instructions
  "Takes a string and returns a list of number corresponding to the instructions
  (number-of-crates-to-move start-stack end-stack)"
  [line]
  (let [match (re-matcher #".*(\d).*(\d).*(\d)" line)]
    (re-find match)
    (map read-string (subvec (re-groups match) 1))))

(get-instructions "move 2 from 1 to 3")
