'use strict';

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {
        $scope.items_transactions = [];
        $scope.items_wallets = [];
        $scope.getItems = function () {
            $http({
                method: "GET",
                url: "api/get",
                headers: {"Content-Type": "application/json"}
            }).then(
                function (data) {
                    console.log(data.data);

                    // transactions.items = data.data;
                    // $scope.items_transactions = data.data;
                    $scope.items_transactions = main_temp.transactions;

                    // wallets.items = data.data;
                    $scope.items_wallets = data.data;
                    // $scope.items_wallets = main_temp.wallets;


                    main.transactions = $scope.items_transactions;
                    main.wallets = $scope.items_wallets;
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
    if (!confirm('Delete all transaction in this wallet?')) return;

    let elem = main.wallets[0];
    for (let i in main.wallets) {
        if (main.wallets[i].id === Number.parseInt(id)) {
            elem = main.wallets[i];
            break;
        }
    }
    let formData = new FormData();
    formData.append('id', elem.id);
    formData.append('name', elem.name);
    formData.append('balance', elem.balance);
    formData.append('currency_id', elem.currency.id);
    formData.append('user_id', elem.user.id);

    let jsonString = formToJson(formData);
    console.log(jsonString);

    await send('api/transaction/deleteAllByWallet', jsonString, errorMsg);

}




let main_temp = {

    wallets:
        [
            {
                "id": 3,
                "name": "euro",
                "currency": {
                    "id": 3,
                    "name": "EUR",
                    "ticker": 0.8
                },
                "balance": 688,
                "user": {
                    "id": 1,
                    "login": "Bilbo Baggins",
                    "password": "burglar",
                    "email": "ubayforever0@gmail.com",
                    "token": null,
                    "validationStatus": 1,
                    "roles": []
                }
            },
            {
                "id": 1,
                "name": "dou123",
                "currency": {
                    "id": 1,
                    "name": "UAN",
                    "ticker": 30
                },
                "balance": 1020,
                "user": {
                    "id": 1,
                    "login": "Bilbo Baggins",
                    "password": "burglar",
                    "email": "ubayforever0@gmail.com",
                    "token": null,
                    "validationStatus": 1,
                    "roles": []
                }
            }
        ],

    transactions: [
        {
            "id": 1,
            "wallet": {
                "id": 1,
                "name": "dou123",
                "currency": {
                    "id": 1,
                    "name": "UAN",
                    "ticker": 30
                },
                "balance": 1020,
                "user": {
                    "id": 1,
                    "login": "Bilbo Baggins",
                    "password": "burglar",
                    "email": "ubayforever0@gmail.com",
                    "token": null,
                    "validationStatus": 1,
                    "roles": []
                }
            },
            "type": "what type is?",
            "category": {
                "id": 7,
                "name": "salary for month",
                "description": "UCODE"
            },
            "tag": {
                "id": 7,
                "name": "onwork",
                "description": "salary"
            },
            "date": "2021-01-15T04:03:16.856+00:00",
            "description": "decembers"
        },
        {
            "id": 2,
            "wallet": {
                "id": 1,
                "name": "dou123",
                "currency": {
                    "id": 1,
                    "name": "UAN",
                    "ticker": 30
                },
                "balance": 1020,
                "user": {
                    "id": 1,
                    "login": "Bilbo Baggins",
                    "password": "burglar",
                    "email": "ubayforever0@gmail.com",
                    "token": null,
                    "validationStatus": 1,
                    "roles": []
                }
            },
            "type": "what type is?",
            "category": {
                "id": 1,
                "name": "apple",
                "description": "food"
            },
            "tag": {
                "id": 5,
                "name": "work stuff",
                "description": "food, paper, etc"
            },
            "date": "2021-01-15T04:03:16.856+00:00",
            "description": "meal"
        },
        {
            "id": 3,
            "wallet": {
                "id": 3,
                "name": "euro",
                "currency": {
                    "id": 3,
                    "name": "EUR",
                    "ticker": 0.8
                },
                "balance": 688,
                "user": {
                    "id": 1,
                    "login": "Bilbo Baggins",
                    "password": "burglar",
                    "email": "ubayforever0@gmail.com",
                    "token": null,
                    "validationStatus": 1,
                    "roles": []
                }
            },
            "type": "what type is?",
            "category": {
                "id": 7,
                "name": "salary for month",
                "description": "UCODE"
            },
            "tag": {
                "id": 7,
                "name": "onwork",
                "description": "salary"
            },
            "date": "2021-01-15T04:03:56.282+00:00",
            "description": "save"
        }
    ]
}
