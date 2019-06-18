const rendersProduct = {
	renderProducts: function(Products) {
		let data = '';

		if (this.Products.length > 0) {
			for (i = 0; i < this.Products.length; i++) {
				data += `<tr id="product${this.Products[i].productId}">`;
				data += `<td>${this.Products[i].productId}</td>`;
				data += `<td>${this.Products[i].productName}</td>`;
				data += `<td>${this.Products[i].ingredients.length}</td>`;

				data += `<td><button type="button" onclick="productApp.setForm('addIngredient', ${this.Products[i].productId}, '${this.Products[i].productName}')" 
                                     class="editbtn btn btn-success" aria-label="Edit">&#9998;</button></td>`;

				data += `<td><button type="button" onclick="productApp.setForm('update', ${this.Products[i].productId}, '${this.Products[i].productName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
				data += `<td><button type="button" onclick="productApp.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.Products[i].productName}"
                data-id="${this.Products[i].productId}" aria-label="Close">&times;</button></td>`;
				data += `</tr>`;
			}
		}
		console.log(this.Products.length);
		this.Count(this.Products.length);
		this.ProductEl.html(data);
	},

	<!--RenderInputField PRODUCT-->
	renderInputFields: function(id) {
		let product = this.Products.filter((el) => el.productId === id)[0];
		if (product) {
			$("#productIdInput").val(product.productId);
			$("#productNameInput").val(product.productName);
		}
	},

    renderInputFieldsIngredients: function(id){
        $("#productIdInput2").val(id);
		$("#rawMatIdInputIngredient").empty();
		let ingredient = this.Products.find(x => x.productId == id).ingredients;

		let populate = this.IngredientsFromDB.filter((x) => {
			return ingredient.find(y => y.rawMatId == x.rawMatID) == undefined;
		})

		populate.map((el) => {
            $("#rawMatIdInputIngredient").append($("<option></option>")
                .attr("value",el.rawMatID)
                .text(el.rawMatName));
        })
	},

    renderIngredientsTable: function(product){
		product.ingredients.map((ingredient) => {
			let ingname = this.IngredientsFromDB.find((y) => y.rawMatID == ingredient.rawMatId).rawMatName
			this.renderAddIngredient(ingredient, ingname, product.productId);

		})
    },

	renderAddIngredient: function (el, name, pid){
		// Use == because rawmatID likely is string...
		$("#ingredientFormTable").append(`<tr id="ing${name + el.amount + el.tolerance}"><td>${name}</td>`+
			`<td>${el.amount}</td><td>${el.tolerance}</td><td><button type="button" onclick="productApp.deleteIngredient(this)" class="closebtn btn btn-danger" 
                data-name="${name}" data-prodid="${pid}"
                data-amount="${el.amount}" data-tolerance="${el.tolerance}" aria-label="Close">&times;</button></td></tr>`)

	},
};