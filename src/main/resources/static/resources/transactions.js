'use strict';

let transactions = {
  items: [],

  openCreate: function() {
    // let addWallet_currency = document.querySelector('#addtransaction_currency');
    // for (let i in transactions.items) {
    //   this.showAddWalletItem(addWallet_currency, currency.items[i]);
    // }
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
    let id = getTransactionId();
    if (id === undefined) return;
    let elem = transactions.items.find(element => element.id === Number.parseInt(id));
    document.querySelector('#updatetransaction_name_head').innerHTML = 'Update ' + elem.name;
    document.querySelector('#update_transaction').setAttribute('class', 'additional_window');
  },
  closeUpdate: function() {
    document.querySelector('#update_transaction').setAttribute('class', 'additional_window hidden');
    let clear = document.querySelectorAll('#update_transaction form input');
    for (let i = 0; i < 2; i++) {
      clear[i].value = '';
    }
  },
  showAddTransactionItem: function(elem, item) {
    let option = document.createElement('option');
    option.setAttribute('currency_id', item.id);
    option.innerHTML = item.name;
    elem.appendChild(option);
  }

}

let init = () => {
  getSelector();
}
window.onload = init;