'use strict';

let getWalletId = () => {
  let item = document.querySelector('.rows[checked="true"]');
  if (item === null || item.className !== "w rows ng-scope") return;
  return item.getElementsByTagName("td")[0].innerHTML;
}

let getCurrencyId = () => {
  let item = document.querySelector('.rows[checked="true"]');
  if (item === null || item.className !== "c rows ng-scope") return;
  return item.getElementsByTagName("td")[0].innerHTML;
}
let getAddWalletCurrency = () => {
  let currency = document.querySelector('#addwallet_currency');
  let arr = currency.getElementsByTagName("option");
  for (let i in arr)
    if (arr[i].value === currency.value)
      return arr[i].getAttribute("currency_id");
}

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
      $scope.items = [];
      $scope.items_currency = [];
      $scope.getItems = function () {
        $http({
          method: "GET",
          url: "api/wallets/get",
          headers: {"Content-Type": "application/json"}
        }).then(
            function (data) {
              console.log(data.data);
              wallets.items = data.data;
              // currency.items =
              $scope.items = data.data;
              $scope.items_currency = currency.items;
            },
            function (error) {
              console.log("error");
              console.log(error);
            }
        );
      }
    }]);

let sendCreateWallet = async () => {
  let name = document.querySelector('#addwallet_name').value;
  let currency_id = getAddWalletCurrency();
  let balance = document.querySelector('#addwallet_balance').value;
  // let icon =

  if (!name || balance < 0) {
    alert('all fields must be filled!');
    return;
  }

  let formData = new FormData();
  formData.append('name', name);
  formData.append('currency_id', currency_id);
  formData.append('balance', balance);
  // formData.append('icon', icon);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  await send('api/wallets/create', jsonString, errorMsg);

}
let sendUpdateWallet = async () => {
  let id = getWalletId();
  if (id === undefined) return;
  let elem = wallets.items.find(element => element.id === Number.parseInt(id));
  let name = document.querySelector('#updatewallet_name').value;
  // let icon =

  if (!name) { // !name && !img to return
    alert('Empty form!');
    return;
  }

  let formData = new FormData();
  formData.append('id', elem.id);
  formData.append('name', name);
  // formData.append('newIcon', icon);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  await send('api/wallets/update', jsonString, errorMsg);

}
let sendDeleteWallet = async () => {
  let id = getWalletId();
  if (id === undefined) return;
  if (!confirm('Delete this wallet?')) return;
  let elem = wallets.items.find(element => element.id === Number.parseInt(id));

  let formData = new FormData();
  formData.append('id', elem.id);
  formData.append('name', elem.name);
  formData.append('balance', elem.balance);
  formData.append('currency_id', elem.currency.id);
  formData.append('user_id', elem.user.id);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  let errorMsg = 'You can\'t delete wallet when it uses in transactions. \n' +
      'Delete or change wallet in corresponding transaction first.';
  await send('api/wallets/delete', jsonString, errorMsg);

}

let sendCreateCurrency = async () => {
  let name = document.querySelector('#addcurrency_name').value;
  let ticker = document.querySelector('#addcurrency_ticker').value;

  if (!name || !ticker) {
    alert('all fields must be filled!');
    return;
  }

  let formData = new FormData();
  formData.append('name', name.toUpperCase());
  formData.append('ticker', ticker);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  await send('api/currency/create', jsonString, errorMsg);

}
let sendUpdateCurrency = async () => {
  let id = getCurrencyId();
  if (id === undefined) return;
  let elem = currency.items.find(element => element.id === Number.parseInt(id));

  let name = document.querySelector('#updatecurrency_name').value;
  let ticker = document.querySelector('#updatecurrency_ticker').value;

  if (!name && !ticker) {
    alert('Empty form!');
    return;
  }

  let formData = new FormData();
  formData.append('id', elem.id);
  formData.append('name', name.toUpperCase());
  formData.append('ticker', ticker);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  await send('api/currency/update', jsonString, errorMsg);

}
let sendDeleteCurrency = async () => {
  let id = getCurrencyId();
  if (id === undefined) return;
  if (!confirm('Delete this currency?')) return;
  let elem = currency.items.find(element => element.id === Number.parseInt(id));

  let formData = new FormData();
  formData.append('id', elem.id);
  formData.append('name', elem.name);
  formData.append('ticker', elem.ticker);

  let jsonString = formToJson(formData);
  console.log(jsonString);

  await send('api/currency/delete', jsonString, errorMsg);

}

