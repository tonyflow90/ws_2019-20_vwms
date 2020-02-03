(ns ttt.core.desktop-launcher
  (:require [ttt.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (LwjglApplication. ttt-game "ttt" 800 600)
  (Keyboard/enableRepeatEvents true))
