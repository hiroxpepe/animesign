
declare var require: any;

import Vue from 'vue';

import HelloComponent from './component/HelloComponent';

///////////////////////////////////////////////////////////////////////////////

export class Application {
    constructor() {
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // public Method
    
    public init() : void {
        let vm = new Vue({
            el: '#vue-app',
            template: require("./Application.html"),
            data: { name: "World" },
            components: {
                HelloComponent
            }
        });
    }
    
}