'use strict';

let main = {
    wallets: [],
    transactions: [],

    showAddWalletItem: function () {
        let elem = document.querySelector('#select_wallet');

        for (let i in this.wallets) {
            let option = document.createElement('option');
            option.setAttribute('wallet_id', this.wallets[i].id);
            option.innerHTML = this.wallets[i].name;
            elem.appendChild(option);
        }
    },
    select_wallet_transaction: function (item) {
        let id = item.options[item.selectedIndex].getAttribute('wallet_id');
        // console.log(id);

        this.showItems(Number.parseInt(id));
    },
    showItems: function(id) {
        let elem = document.querySelector('#transaction_table');

        if (elem.firstChild)
            while (elem.firstChild)
                elem.removeChild(elem.lastChild);

        for (let i in this.transactions)
            this.showItem(elem, this.transactions[i], id);
    },
    showItem: function(elem, item, id) {
        let tr, td;
        if (item.wallet.id !== id && id !== 0)
            return;

        tr = document.createElement('tr');
        tr.className = 'rows';
        {
            td = document.createElement('td');
            td.innerHTML = item.id;
            tr.appendChild(td);
            td = document.createElement('td');
            td.innerHTML = item.description;
            tr.appendChild(td);
            td = document.createElement('td');
            td.innerHTML = item.type;
            tr.appendChild(td);
            td = document.createElement('td');
            td.innerHTML = item.category.name;
            tr.appendChild(td);
            td = document.createElement('td');
            td.innerHTML = item.tag.name;
            tr.appendChild(td);
            td = document.createElement('td');
            td.innerHTML = item.wallet.name;
            tr.appendChild(td);
        }
        elem.appendChild(tr);
    }
}


let init = () => {
  // getSelector();
    main.showAddWalletItem();
    // object.addEventListener("change", myScript);
}
window.onload = init;