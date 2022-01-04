(ns deck-swipe.core
  (:require
   ["react-native" :as rn]
   ["@ilterugur/react-native-deck-swiper-renewed" :refer [Swiper]]
   [shadow.react-native :refer [render-root]]
   [reagent.core :as r]))

(def styles
  ^js (-> {:container
           {:flex            1
            :backgroundColor "#F5FCFF"}
           :card
           {:flex            1
            :borderRadius    4
            :borderWidth     2
            :borderColor     "#E8E8E8"
            :justifyContent  "center"
            :backgroundColor "white"}
           :text
           {:textAlign       "center"
            :fontSize        50
            :backgroundColor "transparent"}}
          (clj->js)
          (rn/StyleSheet.create)))


(defn root
  []
  (fn []
    [:> rn/View {:style (.-container styles)}
     [:> Swiper {:cards           ["do" "more" "of" "what" "makes" "you" "happy"]
                 :renderCard      (fn []
                                    (r/as-element [:> rn/View {:style (.-card styles)}
                                                   [:> rn/Text {:style (.-text styles)} card]]))
                 :onSwiped        #(js/console.log %)
                 :onSwipedAll     #(js/console.log "onSwipedAll")
                 :cardIndex       0
                 :backgroundColor "#4FD0E9"
                 :stackSize       3}]
     [:> rn/Button {:onPress #(js/console.log "oulala")
                    :title   "Press me"}]]))

(defn start
  {:dev/after-load true}
  []
  (render-root "deckSwipeCLJS" (r/as-element [root])))

(defn init []
  (start))
