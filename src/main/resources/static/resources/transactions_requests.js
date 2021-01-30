'use strict';

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
        $scope.items = [];
        $scope.getItems = function () {
            $http({
                method: "GET",
                url: "api/transaction/get",
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                }
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
    let jsonString = getJsonForSendingTransaction('#addtransaction', null);
    if (jsonString === undefined) return;
    await send('api/transaction/create', jsonString, errorMsg);

}
let sendUpdateTransaction = async () => {
    let id = getSelectedRowId('rows ng-scope');
    if (id === undefined) return;
    let elem = transactions.items.find(element => element.id === Number.parseInt(id));
    let jsonString = getJsonForSendingTransaction('#updatetransaction', elem.id);
    await send('api/transaction/update', jsonString, errorMsg);

}
let sendDeleteTransaction = async () => {
    let id = getSelectedRowId('rows ng-scope');
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

    await send('api/transaction/delete', jsonString, errorMsg);

}

let getJsonForSendingTransaction = (elem, elemId) => {
    let date = document.querySelector(elem+'_date').value;
    let description = document.querySelector(elem+'_description').value;
    let type = document.querySelector(elem+'_type').value;
    // let type = getSelectedOptionId(elem+'_type');  // value or id???
    let category_id = getSelectedOptionId(elem+'_category');
    let tag_id = getSelectedOptionId(elem+'_tag');
    let wallet_id = getSelectedOptionId(elem+'_wallet');

    if (elemId === null) {
        if (!description || !date) {
            alert('all fields must be filled!');
            return undefined;
        }
    }

    let transaction = {}
    transaction.description = description;
    transaction.type = type;
    transaction.category = getObjectById(Number.parseInt(category_id), tmp.categories);
    transaction.tag = getObjectById(Number.parseInt(tag_id), tmp.tags);
    transaction.wallet = getObjectById(Number.parseInt(wallet_id), tmp.wallets);
    transaction.date = date;
    if (elemId !== null) {
        transaction.id = elemId;
    }
    console.log(transaction);

    return JSON.stringify(transaction);
}
