
Vue.component('messages-list', {
  template: '<div>List</div>'
});

var app = new Vue({
  el: '#app',
  template: '<messages-list />',
  data: {
    messages: [
        {id: '1', text: "Wow"},
        {id: '2', text: "More"},
        {id: '3', text: "Then"}
    ]
  }
});