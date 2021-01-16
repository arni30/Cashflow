'use strict';

let wallets = {
  items: [],

  openCreate: function() {
    let addWallet_currency = document.querySelector('#addwallet_currency');
    for (let i in currency.items) {
      this.showAddWalletItem(addWallet_currency, currency.items[i]);
    }
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
    let id = getWalletId();
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
  showAddWalletItem: function(elem, item) {
    let option = document.createElement('option');
    option.setAttribute('currency_id', item.id);
    option.innerHTML = item.name;
    elem.appendChild(option);
  }

}

let currency = {
  items: [
    { id: 1, name: 'UAN', ticker: 30 },
    { id: 2, name: 'USD', ticker: 1 },
    { id: 3, name: 'EUR', ticker: 0.8 },
    { id: 4, name: 'TROLO', ticker: 12 }
  ],
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
    let id = getCurrencyId();
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

let tickerExplain = (ticker, name) => {
  return '$100 = ' + 100 * ticker + ' ' + name;
}

let init = () => {
  getSelector();
}
window.onload = init;