function encodeImageFileAsURL(imageIndex) {
    var file = document.getElementById("image" + imageIndex).files[0];  // 根據imageIndex動態獲取圖片檔案
    var reader = new FileReader();
    reader.onloadend = function() {
        document.getElementById("imageBase64_" + imageIndex).value = reader.result;  // 將 Base64 資料賦值到對應的 hidden input
        var preview = document.getElementById("image-preview" + imageIndex);
        preview.innerHTML = '<img src="' + reader.result + '" alt="商品圖片' + imageIndex + '預覽">';  // 顯示預覽圖片
    }
    reader.readAsDataURL(file);  // 將圖片轉換為 Base64 格式
}


function checkProductName() {
    var productName = document.getElementById("name").value;
    if (productName) {
        // 檢查是否存在此商品名稱
        fetch(`/products/check?name=${encodeURIComponent(productName)}`)
            .then(response => {
                if (response.ok) {
                    return response.json(); // 解析成 JSON
                } else {
                    throw new Error("產品不存在1");
                }
            })
            .then(data => {
                console.log("後端返回的產品資料: ", data);

                // 自動填入 type, category, price 和 description，並鎖定它們
                if (data.type && data.type.id) {
                    document.getElementById("type").value = data.type.id;
                    document.getElementById("type").disabled = true; // 鎖定 type
                }

                if (data.category && data.category.id) {
                    document.getElementById("category").value = data.category.id;
                    document.getElementById("category").disabled = true; // 鎖定 category
                }

                if (data.price) {
                    document.getElementById("price").value = data.price;
                    document.getElementById("price").disabled = true; // 鎖定 price
                }

                if (data.description) {
                    document.getElementById("description").value = data.description;
                    document.getElementById("description").disabled = true; // 鎖定 description
                }

                // 清空 size 和 color（因為這些是變體，讓使用者自己選擇）
                document.getElementById("size").value = '';
                document.getElementById("color").value = '';
            })
            .catch(err => {
                console.error("檢查商品時發生錯誤:", err);
                // 如果產品不存在，清空相關欄位並解鎖（允許編輯）
                document.getElementById("type").value = '';
                document.getElementById("type").disabled = false; // 解鎖 type

                document.getElementById("category").value = '';
                document.getElementById("category").disabled = false; // 解鎖 category

                document.getElementById("price").value = '';
                document.getElementById("price").disabled = false; // 解鎖 price

                document.getElementById("description").value = '';
                document.getElementById("description").disabled = false; // 解鎖 description

                document.getElementById("size").value = '';
                document.getElementById("color").value = '';
            });
    }
}




window.onload = function() {
    loadCategories();
    loadTypes();
    loadSizes();
    loadColors();
}    
    

    document.getElementById('product-form').addEventListener('submit', function(event) {
        event.preventDefault();

        // 構建商品數據，包括變體
        var productData = {
            name: document.getElementById("name").value,
            category: document.getElementById("category").value,
            type: document.getElementById("type").value,
            description: document.getElementById("description").value,
            price: document.getElementById("price").value,
            image1: document.getElementById("imageBase64_1").value,  // 第一張圖片
            image2: document.getElementById("imageBase64_2").value,  // 第二張圖片
            image3: document.getElementById("imageBase64_3").value,  // 第三張圖片
            variants: [
                {
                    color: document.getElementById("color").value,
                    size: document.getElementById("size").value,
                    price: document.getElementById("price").value,
                    stock: document.getElementById("stock").value,
                    image1: document.getElementById("imageBase64_1").value,  // 第一張圖片
                    image2: document.getElementById("imageBase64_2").value,  // 第二張圖片
                    image3: document.getElementById("imageBase64_3").value   // 第三張圖片
                }
            ]
        };

        // 發送數據到後端
        fetch('/products/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(productData)
        })
        .then(response => response.json())
        .then(data => {
            alert("商品上架成功！");
        })
        .catch(err => {
            console.error("上架失敗：", err);
        });
    });




function loadCategories() {
    fetch('/categories')
    .then(response => response.json())
    .then(data => {
        var categorySelect = document.getElementById("category");
        categorySelect.innerHTML = '';
        data.forEach(category => {
            var option = document.createElement("option");
            option.value = category.id;
            option.text = category.name;
            categorySelect.appendChild(option);
        });
    });
}

function loadTypes() {
    fetch('/types')
    .then(response => response.json())
    .then(data => {
        var typeSelect = document.getElementById("type");
        typeSelect.innerHTML = '';
        data.forEach(type => {
            var option = document.createElement("option");
            option.value = type.id;
            option.text = type.name;
            typeSelect.appendChild(option);
        });
    });
}

function loadSizes() {
    fetch('/sizes')
    .then(response => response.json())
    .then(data => {
        var sizeSelect = document.getElementById("size");
        sizeSelect.innerHTML = '';
        data.forEach(size => {
            var option = document.createElement("option");
            option.value = size.id;
            option.text = size.name;
            sizeSelect.appendChild(option);
        });
    });
}


function loadColors() {
    fetch('/colors')  
    .then(response => response.json())
    .then(data => {
        var colorSelect = document.getElementById("color");  
        colorSelect.innerHTML = '';  
        data.forEach(color => {
            var option = document.createElement("option");
            option.value = color.id;  
            option.text = color.name;  
            colorSelect.appendChild(option);
        });
    })
    .catch(err => console.error("無法加載顏色選項:", err));
}

document.getElementById('saveColorButton').addEventListener('click', function() {
    var newColorName = document.getElementById("newColorName").value;

    if (newColorName) {
        // 發送顏色數據到後端進行保存
        fetch('/colors/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name: newColorName })
        })
        .then(response => response.json())
        .then(data => {
            // 成功後將顏色加入下拉選單
            var colorSelect = document.getElementById("color");
            var option = document.createElement("option");
            option.text = data.name;
            option.value = data.id;  // 假設後端返回 ID
            colorSelect.add(option);

            // 關閉 Modal 並清空輸入框
            $('#addColorModal').modal('hide');
            document.getElementById("newColorName").value = '';
        })
        .catch(err => {
            console.error("新增顏色失敗：", err);
        });
    } else {
        alert("請輸入顏色名稱！");
    }
});
