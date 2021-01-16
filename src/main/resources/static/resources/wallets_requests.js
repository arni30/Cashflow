'use strict';
let token = document.querySelector('meta[name="_csrf"]').content;

let getWalletId = () => {
  let item = document.querySelector('.rows[checked="true"]');
  if (item !== null && item.className !== "w rows ng-scope") return;
  return item.getElementsByTagName("td")[0].innerHTML;
}

let getCurrencyId = () => {
  let item = document.querySelector('.rows[checked="true"]');
  if (item !== null && item.className !== "c rows ng-scope") return;
  return item.getElementsByTagName("td")[0].innerHTML;
}

// let getData = async () => {
//   await fetch('api/wallets/getWallets', {
//     method: 'GET',
//     cache: 'no-cache',
//     headers: {
//       // 'Content-Type' : 'application/json',
//       'X-CSRF-TOKEN': token
//     },
//     async: true,
//     processData: false
//   }).then((response) => {
//     return response.json();
//   }).then((data) => {
//     console.log(data);
//     wallets.items = data;
//     wallets.showItems();
//     currency.showItems();
//     // location.reload();
//   }).catch((e) => {
//     console.log(e);
//     alert("Can't GET");
//   });
// }

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
      $scope.items = [];
      $scope.items_currency = [];
      $scope.getItems = function () {
        $http({
          method: "GET",
          url: "api/wallets/getWalletsAndCurrency",
          headers: {"Content-Type": "application/json"}
        }).then(
            function (data) {
              console.log(data.data);
              wallets.items = data.data;
              // currency.items =
              $scope.items = data.data;
              $scope.items_currency = currency_to_print(currency.items);
            },
            function (error) {
              console.log("error")
            }
        );
      }
    }]);

let sendCreateWallet = async () => {
  let name = document.querySelector('#addwallet_name').value;
  let currency = document.querySelector('#addwallet_currency').value;
  let balance = document.querySelector('#addwallet_balance').value;
  // let icon =

  if (!name || !currency || balance < 0) {
    alert('all fields must be filled!');
    return;
  }

  let formData = new FormData();
  formData.append('name', name);
  formData.append('currency', currency);
  formData.append('balance', balance);
  // formData.append('icon', icon);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  await fetch('api/wallets/createWallet', {
    method: 'POST',
    cache: 'no-cache',
    headers: {
      'Content-Type' : 'application/json',
      'X-CSRF-TOKEN': token
    },
    async: true,
    processData: false,
    body: jsonString
  }).then((response) => {
    return response.json();
  }).then((data) => {
    console.log(data);
    location.reload();
  }).catch((e) => {
    console.log(e);
    alert("Can't sendCreateWallet");
  });
}

let sendUpdateWallet = async () => {
  let elem = wallets.items.find(element => element.id === Number.parseInt(getWalletId()));
  let name = document.querySelector('#updatewallet_name').value;
  // let icon =

  if (!name) { // !name && !img to return
    alert('Empty form!');
    location.reload();
    return;
  }

  let formData = new FormData();
  formData.append('id', elem.id);
  formData.append('newName', name);
  // formData.append('newIcon', icon);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  await fetch('update_wallet', {
    method: 'POST',
    cache: 'no-cache',
    headers: {
      'Content-Type' : 'application/json',
      'X-CSRF-TOKEN': token
    },
    async: true,
    processData: false,
    body: jsonString
  }).then((response) => {
    return response.json();
  }).then((data) => {
    console.log(data);
    location.reload();
  }).catch((e) => {
    console.log(e);
    alert("Can't sendUpdateWallet");
  });
}
let sendDeleteWallet = async () => {
  if (!confirm('Delete this wallet?')) return;
  let elem = wallets.items.find(element => element.id === Number.parseInt(getCurrencyId()));
  // console.log(elem);
  // wallets.items.splice(wallets.items.indexOf(elem), 1);
  // wallets.showItems();

  let formData = new FormData();
  formData.append('id', elem.id);
  formData.append('name', elem.name);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  await fetch('api/delete_wallet', {
    method: 'POST',
    cache: 'no-cache',
    headers: {
      'Content-Type' : 'application/json',
      'X-CSRF-TOKEN': token
    },
    async: true,
    processData: false,
    body: jsonString
  }).then((response) => {
    return response.json();
  }).then((data) => {
    console.log(data);
    location.reload();
  }).catch((e) => {
    console.log(e);
    alert("Can't sendDeleteWallet");
  });
}

let sendCreateCurrency = async () => {
  let name = document.querySelector('#addcurrency_name').value;
  let ticker = document.querySelector('#addcurrency_ticker').value;

  if (!name || !ticker) {
    alert('all fields must be filled!');
    return;
  }
  let token = document.querySelector('meta[name="_csrf"]').content;
  let formData = new FormData();
  formData.append('name', name);
  formData.append('ticker', ticker);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  await fetch('create_currency', {
    method: 'POST',
    cache: 'no-cache',
    headers: {
      'Content-Type' : 'application/json',
      'X-CSRF-TOKEN': token
    },
    async: true,
    processData: false,
    body: jsonString
  }).then((response) => {
    return response.json();
  }).then((data) => {
    console.log(data);
    location.reload();
  }).catch((e) => {
    console.log(e);
    alert("Can't sendCreateCurrency");
  });
}

let sendUpdateCurrency = async () => {
  let elem = currency.items.find(element => element.id === Number.parseInt(getCurrencyId()));

  let name = document.querySelector('#updatecurrency_name').value;
  let ticker = document.querySelector('#updatecurrency_ticker').value;

  if (!name && !ticker) {
    alert('Empty form!');
    location.reload();
    return;
  }

  let token = document.querySelector('meta[name="_csrf"]').content;
  let formData = new FormData();
  formData.append('id', elem.id);
  formData.append('newName', name);
  formData.append('newTicker', ticker);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  await fetch('update_currency', {
    method: 'POST',
    cache: 'no-cache',
    headers: {
      'Content-Type' : 'application/json',
      'X-CSRF-TOKEN': token
    },
    async: true,
    processData: false,
    body: jsonString
  }).then((response) => {
    return response.json();
  }).then((data) => {
    console.log(data);
    location.reload();
  }).catch((e) => {
    console.log(e);
    alert("Can't sendUpdateWallet");
  });
}

let sendDeleteCurrency = async () => {
  if (!confirm('Delete this currency?')) return;
  let elem = currency.items.find(element => element.id === Number.parseInt(getCurrencyId()));
  // console.log(elem);
  // currency.items.splice(currency.items.indexOf(elem), 1);
  // currency.showItems();

  let formData = new FormData();
  formData.append('id', elem.id);
  formData.append('name', elem.name);

  let jsonString = formToJson(formData);

  await fetch('delete_currency', {
    method: 'POST',
    cache: 'no-cache',
    headers: {
      'Content-Type' : 'application/json',
      'X-CSRF-TOKEN': token
    },
    async: true,
    processData: false,
    body: jsonString
  }).then((response) => {
    return response.json();
  }).then((data) => {
    console.log(data);
    location.reload();
  }).catch((e) => {
    console.log(e);
    alert("Can't sendDeleteCurrency");
  });
}

