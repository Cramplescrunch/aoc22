(ns aoc22.day4
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str]))

(def test-input "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8")

(def real-input (slurp (io/resource "day4.txt")))

(defn read-pair
  "Read an input line (elf pair) and returns a list of number,"
  [line]
  (let [matcher (re-matcher #"(\d+)-(\d+),(\d+)-(\d+)" line)]
    (re-find matcher)
    (vec (map read-string (subvec (re-groups matcher) 1)))))

(defn do-areas-overlap
  "Takes a vector of four number for input (the areas) and
  returns true is the areas overlap, false otherwise."
  [areas]
  (or (and (<= (get areas 0) (get areas 2))
           (>= (get areas 1) (get areas 3)))
      (and (<= (get areas 2) (get areas 0))
           (>= (get areas 3) (get areas 1)))))

(defn count-overlapping-areas
  "Takes a string of pairs and count the overlapping areas"
  [do-overlap-fn pairs]
  (count (filter #(do-overlap-fn %)
                 (map read-pair (str/split-lines pairs)))))

;; Part 1 answer
(count-overlapping-areas do-areas-overlap real-input)

;; Part 2 answer
(defn do-areas-overlap-at-all
  "Takes a vector of four number for input (the areas) and
  returns true is the areas overlap, false otherwise."
  [areas]
  (or (and (<= (get areas 0) (get areas 2))
           (>= (get areas 1) (get areas 3)))
      (and (<= (get areas 2) (get areas 0))
           (>= (get areas 3) (get areas 1)))
      (and (<= (get areas 2) (get areas 1))
           (>= (get areas 2) (get areas 0)))
      (and (<= (get areas 3) (get areas 1))
           (>= (get areas 3) (get areas 0)))
      ))

(count-overlapping-areas do-areas-overlap-at-all real-input)
