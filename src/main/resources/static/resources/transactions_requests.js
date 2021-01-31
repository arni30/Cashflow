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

                    transactions.items = data.data.transactions;
                    $scope.items = data.data.transactions;

                    transactions.wallets = data.data.wallets;
                    transactions.categories = data.data.categories;
                    transactions.tags = data.data.tags;
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
    formData.append('type', elem.type);
    formData.append('description', elem.description);
    formData.append('categoryId', elem.category.id);
    formData.append('tagId', elem.tag.id);
    formData.append('walletId', elem.wallet.id);

    let jsonString = formToJson(formData);
    console.log(jsonString);

    await send('api/transaction/delete', jsonString, errorMsg);

}

let getJsonForSendingTransaction = (elem, elemId) => {
    let date = document.querySelector(elem+'_date').value;
    let description = document.querySelector(elem+'_description').value;
    let type = document.querySelector(elem+'_type').value;
    let category_id = getSelectedOptionId(elem+'_category');
    let tag_id = getSelectedOptionId(elem+'_tag');
    let wallet_id;

    if (elem === '#updatetransaction') {
        wallet_id = '0';
    } else {
        wallet_id = getSelectedOptionId(elem+'_wallet');
    }

    if (elemId === null) {
        if (!description || !date) {
            alert('all fields must be filled!');
            return undefined;
        }
    }

    let transaction = {}
    transaction.description = description;
    transaction.type = type;

    transaction.categoryId = Number.parseInt(category_id);
    transaction.tagId = Number.parseInt(tag_id);
    transaction.walletId = Number.parseInt(wallet_id);

    // transaction.category = getObjectById(Number.parseInt(category_id), transaction.categories);
    // transaction.tag = getObjectById(Number.parseInt(tag_id), transaction.tags);
    // transaction.wallet = getObjectById(Number.parseInt(wallet_id), transaction.wallets);

    transaction.date = date;
    if (elemId !== null) {
        transaction.id = elemId;
    }
    console.log(transaction);

    return JSON.stringify(transaction);
}
