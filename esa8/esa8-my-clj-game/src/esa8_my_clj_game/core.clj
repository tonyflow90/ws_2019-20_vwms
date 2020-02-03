(ns esa8-my-clj-game.core
  (:gen-class))

(def ^:const COMPUTER "X")
(def ^:const HUMAN "O")
(def ^:const initial-game-state (hash-map 0 nil 1 nil 2 nil 3 nil 4 nil 5 nil 6 nil 7 nil 8 nil))

(def ^:const WIN_COMB_HUMAN [(hash-map 0 HUMAN 3 HUMAN 6 HUMAN)
                             (hash-map 1 HUMAN 4 HUMAN 7 HUMAN)
                             (hash-map 2 HUMAN 5 HUMAN 8 HUMAN)
                             (hash-map 0 HUMAN 1 HUMAN 2 HUMAN)
                             (hash-map 3 HUMAN 4 HUMAN 5 HUMAN)
                             (hash-map 6 HUMAN 7 HUMAN 8 HUMAN)
                             (hash-map 0 HUMAN 4 HUMAN 8 HUMAN)
                             (hash-map 2 HUMAN 4 HUMAN 6 HUMAN)])

(def ^:const WIN_COMB_COMPUTER [(hash-map 0 COMPUTER 3 COMPUTER 6 COMPUTER)
                                (hash-map 1 COMPUTER 4 COMPUTER 7 COMPUTER)
                                (hash-map 2 COMPUTER 5 COMPUTER 8 COMPUTER)
                                (hash-map 0 COMPUTER 1 COMPUTER 2 COMPUTER)
                                (hash-map 3 COMPUTER 4 COMPUTER 5 COMPUTER)
                                (hash-map 6 COMPUTER 7 COMPUTER 8 COMPUTER)
                                (hash-map 0 COMPUTER 4 COMPUTER 8 COMPUTER)
                                (hash-map 2 COMPUTER 4 COMPUTER 6 COMPUTER)])


(defn map-contains?
  [map1 map2]
  (let [bool (every? true? (map (fn [[k v]] (= (get map1 k) v)) map2))] bool))

(defn map-contains-winning-comb?
  [map1 arr]
  (let [bool (some true? (for [m arr] (map-contains? map1 m)))] bool))

(defn set-mark
  [game position mark]
  (let [x (merge game {position mark})] x)
  )

(defn set-mark-computer
  [game]
  (def position (rand-int 8))
  (if (not= (get game position) nil)
    (let [x (set-mark-computer game)] x)
    (let [x (set-mark game position COMPUTER)] x)
    )
  )

(defn get-user-input
  []
  (println "Select next field (0-8) > ")
  (let [input (read-line)] input))

(defn is-game-over?
  [game]
  (let [bool (some true? [(map-contains-winning-comb? game WIN_COMB_HUMAN)
                          (map-contains-winning-comb? game WIN_COMB_COMPUTER)]
                   )] bool)
  )

(defn print-game-state
  [game]
  (println (str (get game 0) " | " (game 1) " | " (game 2)))
  (println (str "______"))
  (println (str (get game 3) " | " (game 4) " | " (game 5)))
  (println (str "______"))
  (println (str (get game 6) " | " (game 7) " | " (game 8))))

(defn run
  [game]
  (if (is-game-over? game)
    ((if (map-contains-winning-comb? game WIN_COMB_HUMAN)
       (println "U WON!!")
       )
     (if (map-contains-winning-comb? game WIN_COMB_COMPUTER)
       (println "COMPUTER WON!!")
       )
     (System/exit 0)
     )
    ((print-game-state game)
     (def user-input (get-user-input))
     (def gamemod (set-mark game (read-string user-input) HUMAN))
     (if (is-game-over? gamemod)
       (run gamemod)
       (
        (def gamemod2 (set-mark-computer gamemod))
        (run gamemod2)
        )
       )
     )
    )
  )

(defn -main
  [& args]
  (def game initial-game-state)
  (run game)
  )
