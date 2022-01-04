# deck-swipe-compare
comparing a basic deck swiper in JS vs CLJS

node version v14.15.0

npm version 6.14.8



## JS Repro
`deck-swipe (master) $ react-native init deckSwipeJS`

`deck-swipe (master) $ cd deckSwipeJS/`

`deckSwipeJS (master) $ npm install`

`deckSwipeJS (master) $ npx pod-install`

`deckSwipeJS (master) $ react-native start`

react-native starts without errors!

`deckSwipeJS (master) $ npx react-native run-ios`

It works! default template renders fine

[now to follow instructions for this npm package](https://www.npmjs.com/package/@ilterugur/react-native-deck-swiper-renewed)

`deckSwipeJS (master) $ npm install npm i @ilterugur/react-native-deck-swiper-renewed --save`

restart metro

got into App.JS, import Swiper from swiper lib, import Button from react, replace stylesheet with stylesheet from npm docks, remove Section component, replace render function in App with example from docs

## CLJS repro

`deck-swipe (master) $ mkdir deck-swipe-cljs`

`deck-swipe (master) $ cd deck-swipe-cljs/`

`deck-swipe-cljs (master) $ react-native init deckSwipeCLJS`

`deck-swipe-cljs (master) $ cd deckSwipeCLJS/`

`deckSwipeCLJS (master) $ npm install`

`deckSwipeCLJS (master) $ npm install create-react-class`

`deckSwipeCLJS (master) $ npx pod-install`

`deckSwipeCLJS (master) $ cd ../`

`deck-swipe-cljs (master) $ shadow-cljs init`

edit shadow-cljs.edn to be:

```;; shadow-cljs configuration
{:source-paths
 ["src"]

 :dependencies
 [[org.clojure/clojure "1.10.3"]
  [org.clojure/core.async "1.5.640"]
  [org.clojure/clojurescript "1.10.891"]
  [reagent "1.1.0"]
  [re-frame "1.2.0"]
  [day8.re-frame/re-frame-10x "1.2.0"]
  [day8.re-frame/tracing      "0.6.2"]]

 :builds
 {:app
  {:target     :react-native
   :init-fn    deck-swipe.core/init
   :output-dir "deckSwipeCLJS/app"
   :js-options {:js-package-dirs ["deckSwipeCLJS/node_modules"]}
   :modules    {:client
                {:init-fn deck-swip.core/init}}
   :dev        {:compiler-options {:closure-defines
                                   {re-frame.trace.trace-enabled?        true
                                    day8.re-frame.tracing.trace-enabled? true}}}
   :release    {:build-options
                {:ns-aliases
                 {day8.re-frame.tracing day8.re-frame.tracing-stubs}}}}}}
```


make App.js look like :



```/**
 * @format
 */
import './app/index.js';
```

`deck-swipe-cljs (master) $ mkdir -p src/deck_swipe && cd src/deck_swipe && touch core.cljs`

make core.cljs look like:
```
(ns deck-swipe.core
  (:require
   [shadow.react-native :refer (render-root)]
   ["react-native" :as rn]
   [reagent.core :as r]))

(def styles
  ^js (-> {:container
           {:flex            1
            :backgroundColor "#fff"
            :alignItems      "center"
            :justifyContent  "center"}
           :title
           {:fontWeight "bold"
            :fontSize   24
            :color      "blue"}}
          (clj->js)
          (rn/StyleSheet.create)))

(defn root []
  [:> rn/View {:style (.-container styles)}
   [:> rn/Text {:style (.-title styles)} "Hello!"]])

(defn start
  {:dev/after-load true}
  []
  (render-root "deckSwipeCLJS" (r/as-element [root])))

(defn init []
  (start))
```


`deck_swipe (master) $ cd ../../`

`deck-swipe-cljs (master) $ shadow-cljs watch app`

works!

`deck-swipe-cljs (master) $ cd deckSwipeCLJS/`

`deckSwipeCLJS (master) $ react-native start`

Works! now to add Swiper

`deckSwipeCLJS (master) $ npm install npm i @ilterugur/react-native-deck-swiper-renewed --save`

restart metro

go to core.cljs and recreate example swiper from docs in cljs

and boom it breaks


```
Error: Element type is invalid: expected a string (for built-in components) or a class/function (for composite components) but got: undefined. You likely forgot to export your component from the file it's defined in, or you might have mixed up default and named imports.

Check the render method of `deck_swipe.core.root`.

This error is located at:
    in RCTView (at View.js:32)
    in View (created by deck_swipe.core.root)
    in deck_swipe.core.root
    in Unknown (at renderApplication.js:50)
    in RCTView (at View.js:32)
    in View (at AppContainer.js:92)
    in RCTView (at View.js:32)
    in View (at AppContainer.js:119)
    in AppContainer (at renderApplication.js:43)
    in deckSwipeCLJS(RootComponent) (at renderApplication.js:60)
```

When I was creating the JS app i got this exact error once when i accidentally deleted `export default App;`
on line 107 in App.js. So i feel like there is some interop piece that i am missing.
