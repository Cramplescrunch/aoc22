(ns aoc22.day3
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str]))

(def test-input "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw")

(def real-input (slurp (io/resource "day3.txt")))

(def priority-map
  (into {}
        (list
         (zipmap (map char (range (int \a) (inc (int \z)))) (range 1 27))
         (zipmap (map char (range (int \A) (inc (int \Z)))) (range 27 53)))))

(defn split-rucksack-comp
  "Split a given ruckasck in half.
  Returns a vector with two strings representign each compartment."
  [string]
  (let [length (count string)
        half (/ length 2)]
    [(subs string 0 half)
     (subs string half length)]))

(defn find-duplicate
  "Takes a vector of two string (split rucksack) and finds the duplicate item.
  Returns a char."
  [split-rucksack]
  (let [first-comp (set (first split-rucksack))
        second-comp (set (second split-rucksack))]
    (first (filter #(contains? first-comp %) second-comp))))

(defn get-priority-from-rucksack
  "Extract duplicate item for given rucksack and returns priority of that item"
  [rucksack-line]
  (get priority-map (find-duplicate (split-rucksack-comp rucksack-line))))

(defn compute-item-types-sum
  [input]
  (reduce + (map get-priority-from-rucksack (str/split-lines input))))

;; Part 1 answer
(compute-item-types-sum real-input)

;; Part 2 specific code
(defn find-group-badge
  "Takes three strings, each representing the rucksack of an elf.
  Finds the duplicate item type between each rucksack.
  Returns a char representing the badge of the group."
  [elf1, elf2, elf3]
  (let [set1 (set elf1)
        set2 (set elf2)
        set3 (set elf3)]
    (first (filter #(contains? set3 %)
                   (filter #(contains? set1 %) set2)))))

(apply find-group-badge (take 3 (str/split-lines test-input)))

(defn get-group-badges-from-input
  "Given a string input with one rucksack on each line,
  returns all badges for every group of three elves as a list of characters."
  [input]
  (map
   #(apply find-group-badge %)
   (partition 3 (str/split-lines input))))
;; For example: returns (\r \Z) for test-input

(defn compute-badges-sum
  [input]
  (reduce + (map #(get priority-map %) (get-group-badges-from-input input))))

;; Part 2 answer
(compute-badges-sum real-input)
