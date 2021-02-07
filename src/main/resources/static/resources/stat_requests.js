'use strict';

let walletId = 0;
let startDate;
let endDate;

let urlParams = new URLSearchParams(window.location.search);
if (urlParams.has('walletId')) {
    walletId = urlParams.get('walletId');
    startDate = urlParams.get('startDate');
    endDate = urlParams.get('endDate');
} else {
    startDate = document.querySelector("#select_startTime").value;
    endDate = document.querySelector("#select_endTime").value;
}

let stat = {
    items: [],

    wallets: [],
    categories: [],
    tags: []
}

angular.module("get_form", [])
    .controller("GetController", ["$scope", "$http", function ($scope, $http) {

        $scope.items = [];

        $scope.getItems = function () {
            $http({
                method: "GET",
                url: "api/stat/get30"
                    + '?walletId=' + walletId
                    + "&startDate=" + startDate
                    + "&endDate=" + endDate,
                headers: {
                    "Content-Type": "application/json",
                    'X-CSRF-TOKEN': token
                }
            }).then(
                function (data) {
                    console.log(data.data);

                    $scope.items = data.data.transactions;
                    $scope.total = data.data.total;
                    $scope.wallets = data.data.wallets;

                    stat.items = data.data.transactions;
                    stat.wallets = data.data.wallets;
                    stat.categories = data.data.categories;
                    stat.tags = data.data.tags;
                },
                function (error) {
                    console.log("error")
                    alert("Wrong request parameters for getting info from server");
                }
            );
        }

        // $scope.filters = function () {
        //     $http({
        //         method: "POST",
        //         cache: 'no-cache',
        //         url: "api/stat/filters",
        //         headers: {
        //             "Content-Type": "application/json",
        //             'X-CSRF-TOKEN': token
        //         },
        //         processData: false,
        //         body: jsonString
        //     }).then(
        //         function (data) {
        //             console.log(data.data);
        //
        //             // tmp.transactions = data.data;
        //             // $scope.items = data.data;
        //         },
        //         function (error) {
        //             console.log("error")
        //         }
        //     );
        // }

    }]);

let replace = () => {
    startDate = document.querySelector("#select_startTime").value;
    endDate = document.querySelector("#select_endTime").value;
    location.replace(
        '/statistics?walletId=' + walletId
        + "&startDate=" + startDate
        + "&endDate=" + endDate
    );
}

let formData = new FormData();
// formData.append('id', elem.id);
// formData.append('name', elem.description);
// formData.append('category_id', elem.category.id);
formData.append('startDate', document.querySelector("#select_startTime").value);
formData.append('endDate', document.querySelector("#select_endTime").value);

let jsonString = formToJson(formData);
console.log(jsonString);


let init = () => {
    // addItemsToSelect('#addtransaction_wallet', stat.wallets);
    // addItemsToSelect('#addtransaction_category', stat.categories);
    // addItemsToSelect('#addtransaction_tag', stat.tags);

    // startDate = document.querySelector("#select_startTime").value;
    // endDate = document.querySelector("#select_endTime").value;
}
window.onload = init;