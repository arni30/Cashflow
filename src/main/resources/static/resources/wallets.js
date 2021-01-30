'use strict';

let wallets = {
  items: [],

  openCreate: function() {
    addItemsToSelect('#addwallet_currency', currency.items);
    document.querySelector('#create_wallet').setAttribute('class', 'additional_window');
  },
  closeCreate: function() {
    document.querySelector('#create_wallet').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#create_wallet form input');
    for (let i = 0; i < 3; i++) {
      clear[i].value = '';
    }
  },
  openUpdate: function() {
    let id = getSelectedRowId("w rows ng-scope");
    if (id === undefined) return;
    let elem = wallets.items.find(element => element.id === Number.parseInt(id));
    document.querySelector('#updatewallet_name_head').innerHTML = 'Update ' + elem.name;
    document.querySelector('#update_wallet').setAttribute('class', 'additional_window');
  },
  closeUpdate: function() {
    document.querySelector('#update_wallet').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#update_wallet form input');
    for (let i = 0; i < 2; i++) {
      clear[i].value = '';
    }
  },

}

let currency = {
  items: [],
  openCreate: function() {
    document.querySelector('#create_currency').setAttribute('class', 'additional_window');
  },
  closeCreate: function() {
    document.querySelector('#create_currency').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#create_currency form input');
    for (let i = 0; i < 2; i++) {
      clear[i].value = '';
    }
  },
  openUpdate: function() {
    let id = getSelectedRowId("c rows ng-scope");
    if (id === undefined) return;
    let elem = currency.items.find(element => element.id === Number.parseInt(id));
    document.querySelector('#updatecurrency_name_head').innerHTML = 'Update ' + elem.name;
    document.querySelector('#update_currency').setAttribute('class', 'additional_window');
  },
  closeUpdate: function() {
    document.querySelector('#update_currency').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#update_currency form input');
    for (let i = 0; i < 2; i++) {
      clear[i].value = '';
    }
  }

}

let init = () => {
  getSelector();
}
window.onload = init;