const rendersProdBatch = {
	renderProductBatches: function(ProductBatches) {
		let data = '';

		if (this.ProductBatches.length > 0) {
			for (i = 0; i < this.ProductBatches.length; i++) {
				data += `<tr id="prodbatch${this.ProductBatches[i].prodBatchId}">`;
				data += `<td>${this.ProductBatches[i].name}</td>`;
				data += `<td>${this.ProductBatches[i].prodBatchId}</td>`;
				data += `<td>${this.ProductBatches[i].prodId}</td>`;
				data += `<td>${this.ProductBatches[i].status}</td>`;
				//data += `<td>${this.ProductBatches[i].userId}</td>`;
				data += `<td>${this.ProductBatches[i].weighings.length}</td>`;

				data += `<td><button type="button" onclick="prodBatchApp.setForm('update', ${this.ProductBatches[i].productBatchId}, '${this.ProductBatches[i].productBatchName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
				data += `<td><button type="button" onclick="prodBatchApp.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.ProductBatches[i].productBatchName}"
                data-id="${this.ProductBatches[i].productBatchId}" aria-label="Close">&times;</button></td>`;
				data += `</tr>`;
			}
		}
		console.log(this.ProductBatches.length);
		this.Count(this.ProductBatches.length);
		this.RroductBatchEl.html(data);
	},

	<!--RenderInputField PRODUKTBATCH-->
	renderInputFields: function(id) {
		let productBatch = this.ProductBatches.filter((el) => el.productBatchId === id)[0];
		if (productBatch) {
			$("#productBatchIdInput").val(productBatch.productBatchId);
			$("#receptIdInput").val(productBatch.receptId);
			$("#statusInput").val(productBatch.status);
			$("#userIDInput").val(productBatch.userId);
			$("#RMBIdInput").val(productBatch.RMBId);
			$("#taraInput").val(productBatch.tara);
			$("#nettoInput").val(productBatch.netto);
		}
	}
};