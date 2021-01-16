'use strict';

let getTransactionId = () => {
    let item = document.querySelector('.rows[checked="true"]');
    if (item === null || item.className !== "rows ng-scope") return;
    return item.getElementsByTagName("td")[0].innerHTML;
}

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
        $scope.items = [];
        $scope.getItems = function () {
            $http({
                method: "GET",
                url: "api/transaction/get",
                headers: {"Content-Type": "application/json"}
            }).then(
                function (data) {
                    console.log(data.data);

                    transactions.items = data.data;
                    $scope.items = data.data;
                },
                function (error) {
                    console.log("error")
                }
            );
        }
    }]);

let sendCreateTransaction = async () => {
    // let name = document.querySelector('#addtransaction_name').value;
    // let currency_id = getAddWalletCurrency();
    // let balance = document.querySelector('#addtransaction_balance').value;
    // // let icon =
    //
    // if (!name || balance < 0) {
    //     alert('all fields must be filled!');
    //     return;
    // }
    //
    // let formData = new FormData();
    // formData.append('name', name);
    // formData.append('currency_id', currency_id);
    // formData.append('balance', balance);
    // // formData.append('icon', icon);
    //
    // let jsonString = formToJson(formData);
    // console.log(jsonString);
    //
    // await send('api/wallets/create', jsonString, errorMsg);

}
let sendUpdateTransaction = async () => {
    // let id = getWalletId();
    // if (id === undefined) return;
    // let elem = wallets.items.find(element => element.id === Number.parseInt(id));
    // let name = document.querySelector('#updatewallet_name').value;
    // // let icon =
    //
    // if (!name) { // !name && !img to return
    //     alert('Empty form!');
    //     return;
    // }
    //
    // let formData = new FormData();
    // formData.append('id', elem.id);
    // formData.append('name', name);
    // // formData.append('newIcon', icon);
    //
    // let jsonString = formToJson(formData);
    // console.log(jsonString);
    //
    // await send('api/wallets/update', jsonString, errorMsg);

}
let sendDeleteTransaction = async () => {
    let id = getTransactionId();
    if (id === undefined) return;
    if (!confirm('Delete this transaction?')) return;
    let elem = transactions.items.find(element => element.id === Number.parseInt(id));

    let formData = new FormData();
    formData.append('id', elem.id);
    formData.append('name', elem.description);
    formData.append('category_id', elem.category.id);
    formData.append('tag_id', elem.tag.id);
    formData.append('wallet_id', elem.wallet.id);

    let jsonString = formToJson(formData);
    console.log(jsonString);

    let errorMsg = 'немає обробки запроса на сервері';
    await send('api/transaction/delete', jsonString, errorMsg);

}
