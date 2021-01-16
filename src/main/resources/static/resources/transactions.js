'use strict';

let transactions = {
  items: [
    { id: 4, img: 'resources/img/logo.png', name: 'Wallet',
      balance: 540.00,  currency: 'uan'
    }
  ],

  openCreate: function() {
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
    let item = document.querySelector('input[name=wallet]:checked');
    if (item === null) return;
    let id = item.getAttribute('wallet_id');
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
  }

  // showItems: function() {
  //   let elem = document.querySelector('#wallets_block');
  //
  //   if (elem.firstChild)
  //     while (elem.firstChild)
  //       elem.removeChild(elem.lastChild);
  //
  //   for (let i in this.items)
  //     this.showItem(elem, this.items[i]);
  // }
//   showItem: function(elem, item) {
//     let input, label, div, span;
//
//     input = document.createElement('input');
//     input.setAttribute('id', 'wallet' + item.id);
//     input.setAttribute('wallet_id', item.id);
//     input.setAttribute('type', 'radio');
//     input.setAttribute('name', 'wallet');
//     elem.appendChild(input);
//
//     label = document.createElement('label');
//     label.setAttribute('for', 'wallet' + item.id);
//     {
//       div = document.createElement('div');
//       div.className = 'wallet_img';
//       {
//         span = document.createElement('img');
//         span.setAttribute('src', (item.img !== undefined) ? item.img : 'resources/img/no-img.png');
//         div.appendChild(span);
//       }
//       label.appendChild(div);
//
//       span = document.createElement('span');
//       span.className = 'wallet_name';
//       span.innerHTML = item.name;
//       label.appendChild(span);
//
//       span = document.createElement('span');
//       span.innerHTML = item.balance;
//       label.appendChild(span);
//
//       span = document.createElement('span');
//       span.className = 'wallet_currency';
//       span.innerHTML = item.currency.name;
//       label.appendChild(span);
//     }
//     elem.appendChild(label);
//   }
}

// let currency = {
//   items: [
//     { id: 1, name: 'uan', ticker: 27 },
//     { id: 4, name: 'usd', ticker: 1 },
//     { id: 5, name: 'eur', ticker: 0.82 }
//   ],
//   openCreate: function() {
//     document.querySelector('#create_currency').setAttribute('class', 'additional_window');
//   },
//   closeCreate: function() {
//     document.querySelector('#create_currency').setAttribute('class', 'additional_window hidden');
//     let clear = document.querySelectorAll('#create_currency form input');
//     for (let i = 0; i < 2; i++) {
//       clear[i].value = '';
//     }
//   },
//   openUpdate: function() {
//     let item = document.querySelector('input[name=currency]:checked');
//     if (item === null) return;
//     let id = item.getAttribute('currency_id');
//     let elem = currency.items.find(element => element.id === Number.parseInt(id));
//     document.querySelector('#updatecurrency_name_head').innerHTML = 'Update ' + elem.name;
//     document.querySelector('#update_currency').setAttribute('class', 'additional_window');
//   },
//   closeUpdate: function() {
//     document.querySelector('#update_currency').setAttribute('class', 'additional_window hidden');
//     let clear = document.querySelectorAll('#update_currency form input');
//     for (let i = 0; i < 2; i++) {
//       clear[i].value = '';
//     }
//   },
//
//   showItems: function () {
//     let elem = document.querySelector('#currency_block');
//     let addwallet_currency = document.querySelector('#addwallet_currency');
//
//     if (elem.firstChild)
//       while (elem.firstChild)
//         elem.removeChild(elem.lastChild);
//
//     if (addwallet_currency.firstChild)
//       while (addwallet_currency.firstChild)
//         addwallet_currency.removeChild(addwallet_currency.lastChild);
//
//     for (let i in this.items) {
//       this.showItem(elem, this.items[i]);
//       this.showAddWalletItem(addwallet_currency, this.items[i]);
//     }
//   },
//   showAddWalletItem: function(elem, item) {
//     let option = document.createElement('option');
//     option.setAttribute('currency_id', item.id);
//     option.innerHTML = item.name;
//     elem.appendChild(option);
//   },
//   showItem: function(elem, item) {
//     let input, label, span;
//
//     input = document.createElement('input');
//     input.setAttribute('id', 'currency' + item.id);
//     input.setAttribute('currency_id', item.id);
//     input.setAttribute('type', 'radio');
//     input.setAttribute('name', 'currency');
//     elem.appendChild(input);
//
//     label = document.createElement('label');
//     label.setAttribute('for', 'currency' + item.id);
//     {
//       span = document.createElement('p');
//       span.innerHTML = item.name;
//       label.appendChild(span);
//
//       span = document.createElement('p');
//       span.innerHTML = '$100 = ' + 100*item.ticker + ' ' + item.name;
//       label.appendChild(span);
//     }
//     elem.appendChild(label);
//   }
// }

let init = () => {
  getSelector();
}
window.onload = init;