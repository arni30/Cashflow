'use strict';

let transactions = {
  items: [],

  wallets: [],
  categories: [],
  tags: [],
  type: [
    { id: 1, name: 'income'},
    { id: 2, name: 'expense'},
  ],

  openCreate: function() {
    addItemsToSelect('#addtransaction_type', this.type);
    addItemsToSelect('#addtransaction_category', this.categories);
    addItemsToSelect('#addtransaction_tag', this.tags);
    addItemsToSelect('#addtransaction_wallet', this.wallets);
    document.querySelector('#create_transaction').setAttribute('class', 'additional_window');
  },
  closeCreate: function() {
    document.querySelector('#create_transaction').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#create_transaction form input');
    for (let i = 0; i < 3; i++) {
      clear[i].value = '';
    }
  },
  openUpdate: function(event) {
    let id = event.getAttribute('tid');
    let elem = transactions.items.find(element => element.id === Number.parseInt(id));
    document.querySelector('#updatetransaction_name_head').innerHTML = 'Update ' + elem.name;
    addItemsToSelect('#updatetransaction_type', this.type);
    addItemsToSelect('#updatetransaction_category', this.categories);
    addItemsToSelect('#updatetransaction_tag', this.tags);
    // addItemsToSelect('#updatetransaction_wallet', this.wallets);
    document.querySelector('#updateButton')
        .setAttribute('onclick', 'sendUpdateTransaction(' + id + ')')
    document.querySelector('#update_transaction').setAttribute('class', 'additional_window');
  },
  closeUpdate: function() {
    document.querySelector('#update_transaction').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#update_transaction form input');
    for (let i = 0; i < 2; i++) {
      clear[i].value = '';
    }
  }

}

let init = () => {
  // getSelector();
}
window.onload = init;