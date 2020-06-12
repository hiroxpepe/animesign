declare var require: any;

import Vue from 'vue';
//import $http from 'axios'

// Vue.prototype.$http = axios; // guro-baru 

export default Vue.extend({
    props: ['name', 'initialEnthusiasm'],
    template: require("./HelloComponent.html"),
    data() {
        return {
            enthusiasm: this.initialEnthusiasm,
        }
    },
    methods: {
        increment() { this.enthusiasm++; },
        decrement() {
            if (this.enthusiasm > 1) {
                this.enthusiasm--;
            }
        },
    },
    computed: {
        exclamationMarks(): string {
            return Array(this.enthusiasm + 1).join('!');
        }
    }
});