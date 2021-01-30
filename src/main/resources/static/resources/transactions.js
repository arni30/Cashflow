'use strict';

let transactions = {
  items: [],

  openCreate: function() {
    addItemsToSelect('#addtransaction_type', tmp.type);
    addItemsToSelect('#addtransaction_category', tmp.categories);
    addItemsToSelect('#addtransaction_tag', tmp.tags);
    addItemsToSelect('#addtransaction_wallet', tmp.wallets);
    document.querySelector('#create_transaction').setAttribute('class', 'additional_window');
  },
  closeCreate: function() {
    document.querySelector('#create_transaction').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#create_transaction form input');
    for (let i = 0; i < 3; i++) {
      clear[i].value = '';
    }
  },
  openUpdate: function() {
    let id = getSelectedOptionId('rows ng-scope');
    if (id === undefined) return;
    let elem = transactions.items.find(element => element.id === Number.parseInt(id));
    document.querySelector('#updatetransaction_name_head').innerHTML = 'Update ' + elem.name;
    addItemsToSelect('#updatetransaction_type', tmp.type);
    addItemsToSelect('#updatetransaction_category', tmp.categories);
    addItemsToSelect('#updatetransaction_tag', tmp.tags);
    addItemsToSelect('#updatetransaction_wallet', tmp.wallets);
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
  getSelector();
}
window.onload = init;