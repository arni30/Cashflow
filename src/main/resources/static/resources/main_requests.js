'use strict';

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
        $scope.items_transactions = [];
        $scope.items_wallets = [];
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

                    $scope.items_transactions = data.data.transactions;
                    $scope.items_wallets = data.data.wallets;

                    main.transactions = data.data.transactions;
                    main.wallets = data.data.wallets;
                },
                function (error) {
                    console.log("error")
                }
            );
        }
    }]);

let sendDeleteAllTransactions = async () => {
    let item = document.querySelector('#select_wallet');
    let id = item.options[item.selectedIndex].getAttribute('wallet_id');
    if (id === '0') {
        alert('invalid wallet');
        return;
    }
    if (!confirm('Delete all transaction with this wallet?')) return;

    let elem = main.wallets[0];
    for (let i in main.wallets) {
        if (main.wallets[i].id === Number.parseInt(id)) {
            elem = main.wallets[i];
            break;
        }
    }
    // let formData = new FormData();
    // formData.append('id', elem.id);
    // formData.append('name', elem.name);
    // formData.append('balance', elem.balance);
    // formData.append('currencyId', elem.currency.id);

    let wallet = {}
    wallet.id = elem.id;
    wallet.name = elem.name;
    wallet.currencyId = elem.currency.id;
    wallet.balance = elem.balance;

    let jsonString = JSON.stringify(wallet);
    console.log(jsonString);

    await send('api/transaction/deleteAllByWallet', jsonString, errorMsg);

}

