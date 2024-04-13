(ns aoc22.day1
  (:require [clojure.string :as str])
  (:require [clojure.java.io :as io]))

(def test-input "1000
2000
3000

4000

5000
6000

7000
8000
9000

10000")

(def real-input (slurp (io/resource "day1.txt")))

(defn group-by-elf
  [input]
  (str/split input #"\n\n"))

(defn get-sum
  [input]
  (reduce + (map read-string (str/split-lines input))))

;; Part 1 answer
(reduce max (map get-sum (group-by-elf real-input)))

;; Part 2 answer
(def sums-by-elves (map get-sum (group-by-elf real-input)))

(reduce + (take 3 (sort > sums-by-elves)))
