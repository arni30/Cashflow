'use strict';
let token = document.querySelector('meta[name="_csrf"]').content;

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
        $scope.items = [];
        $scope.getItems = function () {
            $http({
                method: "GET",
                url: "api/category/getCategoriesAngTags",
                headers: {"Content-Type": "application/json"}
            }).then(
                function (data) {
                    console.log(data.data);
                    $scope.items = data.data;
                    categories.items = data.data;
                },
                function (error) {
                    console.log("error")
                }
            );
        }
    }]);

//
// let sendCreateWallet = async () => {
//   let name = document.querySelector('#addwallet_name').value;
//   let currency = document.querySelector('#addwallet_currency').value;
//   let balance = document.querySelector('#addwallet_balance').value;
//   // let icon =
//
//   if (!name || !currency || balance < 0) {
//     alert('all fields must be filled!');
//     return;
//   }
//   let formData = new FormData();
//   formData.append('name', name);
//   formData.append('currency', currency);
//   formData.append('balance', balance);
//   // formData.append('icon', icon);
//
//   let jsonString = formToJson(formData);
//   console.log(jsonString);
//
//   await fetch('api/wallets/createWallet', {
//     method: 'POST',
//     cache: 'no-cache',
//     headers: {
//       'Content-Type' : 'application/json',
//       'X-CSRF-TOKEN': token
//     },
//     async: true,
//     processData: false,
//     body: jsonString
//   }).then((response) => {
//     return response.json();
//   }).then((data) => {
//     console.log(data);
//     location.reload();
//   }).catch((e) => {
//     console.log(e);
//     alert("Can't sendCreateWallet");
//   });
// }
// let sendUpdateWallet = async () => {
//   let item = document.querySelector('input[name=wallet]:checked');
//   let id = item.getAttribute('wallet_id');
//   let elem = wallets.items.find(element => element.id === Number.parseInt(id));
//   let name = document.querySelector('#updatewallet_name').value;
//   // let icon =
//
//   if (!name) { // !name && !img to return
//     alert('Empty form!');
//     location.reload();
//     return;
//   }
//
//   let formData = new FormData();
//   formData.append('id', elem.id);
//   formData.append('newName', name);
//   // formData.append('newIcon', icon);
//
//   let jsonString = formToJson(formData);
//   console.log(jsonString);
//
//   await fetch('update_wallet', {
//     method: 'POST',
//     cache: 'no-cache',
//     headers: {
//       'Content-Type' : 'application/json',
//       'X-CSRF-TOKEN': token
//     },
//     async: true,
//     processData: false,
//     body: jsonString
//   }).then((response) => {
//     return response.json();
//   }).then((data) => {
//     console.log(data);
//     location.reload();
//   }).catch((e) => {
//     console.log(e);
//     alert("Can't sendUpdateWallet");
//   });
// }
// let sendDeleteWallet = async () => {
//   let item = document.querySelector('input[name=wallet]:checked');
//   if (item === null) return;
//   if (!confirm('Delete this wallet?')) return;
//
//   let id = item.getAttribute('wallet_id');
//   let elem = wallets.items.find(element => element.id === Number.parseInt(id));
//   // console.log(elem);
//   // wallets.items.splice(wallets.items.indexOf(elem), 1);
//   // wallets.showItems();
//
//   let token = document.querySelector('meta[name="_csrf"]').content;
//   let formData = new FormData();
//   formData.append('id', elem.id);
//   formData.append('name', elem.name);
//
//   let jsonString = formToJson(formData);
//   console.log(jsonString);
//
//   await fetch('api/delete_wallet', {
//     method: 'POST',
//     cache: 'no-cache',
//     headers: {
//       'Content-Type' : 'application/json',
//       'X-CSRF-TOKEN': token
//     },
//     async: true,
//     processData: false,
//     body: jsonString
//   }).then((response) => {
//     return response.json();
//   }).then((data) => {
//     console.log(data);
//     wallets.items.splice(wallets.items.indexOf(elem), 1);
//     wallets.showItems();
//   }).catch((e) => {
//     console.log(e);
//     alert("Can't sendDeleteWallet");
//   });
// }

