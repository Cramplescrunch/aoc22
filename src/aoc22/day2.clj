(ns aoc22.day2
  (:require [clojure.string :as str])
  (:require [clojure.java.io :as io]))

(def test-input "A Y
B X
C Z")

(def real-input (slurp (io/resource "day2.txt")))

(defn compute-total-score
  [scores, input]
  (reduce + (map #(get scores %) (str/split-lines input))))

;; Part 1 answer
(def p1-scores
  {"A X" (+ 3 1),
   "A Y" (+ 6 2),
   "A Z" (+ 0 3),
   "B X" (+ 0 1),
   "B Y" (+ 3 2),
   "B Z" (+ 6 3),
   "C X" (+ 6 1),
   "C Y" (+ 0 2),
   "C Z" (+ 3 3)
   })

(compute-total-score p1-scores real-input)

;; Part 2 answer
(def p2-scores

  {"A X" (+ 0 3),
   "A Y" (+ 3 1),
   "A Z" (+ 6 2),
   "B X" (+ 0 1),
   "B Y" (+ 3 2),
   "B Z" (+ 6 3),
   "C X" (+ 0 2),
   "C Y" (+ 3 3),
   "C Z" (+ 6 1)
   })


(compute-total-score p2-scores real-input)
