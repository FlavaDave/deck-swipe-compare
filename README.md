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

`deckSwipeCLJS (master) $ npx pod-install`

`deckSwipeCLJS (master) $ cd ../`

`shadow-cljs init`

edit shadow-cljs.edn to be:

``````
