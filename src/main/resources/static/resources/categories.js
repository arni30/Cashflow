'use strict';

let categories = {
  items: [],

  openCreate: function() {
    document.querySelector('#create_category').setAttribute('class', 'additional_window');
  },
  closeCreate: function() {
    document.querySelector('#create_category').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#create_category form input');
    for (let i = 0; i < 3; i++) {
      clear[i].value = '';
    }
  },
  openUpdate: function() {
    let id = getCategoryId();
    if (id === undefined) return;
    let elem = categories.items.find(element => element.id === Number.parseInt(id));
    document.querySelector('#updatecategory_name_head').innerHTML = 'Update ' + elem.name;
    document.querySelector('#update_category').setAttribute('class', 'additional_window');
  },
  closeUpdate: function() {
    document.querySelector('#update_category').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#update_category form input');
    for (let i = 0; i < 2; i++) {
      clear[i].value = '';
    }
  }
}

let tags = {
  items: [],
  openCreate: function() {
    document.querySelector('#create_tag').setAttribute('class', 'additional_window');
  },
  closeCreate: function() {
    document.querySelector('#create_tag').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#create_tag form input');
    for (let i = 0; i < 3; i++) {
      clear[i].value = '';
    }
  },
  openUpdate: function() {
    let id = getTagId();
    if (id === undefined) return;
    let elem = tags.items.find(element => element.id === Number.parseInt(id));
    document.querySelector('#updatetag_name_head').innerHTML = 'Update ' + elem.name;
    document.querySelector('#update_tag').setAttribute('class', 'additional_window');
  },
  closeUpdate: function() {
    document.querySelector('#update_tag').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#update_tag form input');
    for (let i = 0; i < 2; i++) {
      clear[i].value = '';
    }
  }
}

let init = () => {
  getSelector();
}
window.onload = init;