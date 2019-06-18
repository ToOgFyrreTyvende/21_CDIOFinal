const rendersRawBatch = {
	renderRawMaterialBatches: function(RawMaterialBatches) {
		let data = '';

		if (this.RawMaterialBatches.length > 0) {
			for (i = 0; i < this.RawMaterialBatches.length; i++) {
				data += `<tr id="rmb${this.RawMaterialBatches[i].rmbId}">`;
				data += `<td>${this.RawMaterialBatches[i].name}</td>`;
				data += `<td>${this.RawMaterialBatches[i].rmbId}</td>`;
				data += `<td>${this.RawMaterialBatches[i].rawMatId}</td>`;
				data += `<td>${this.RawMaterialBatches[i].amount}</td>`;

				data += `<td><button type="button" onclick="rawMatBatApp.setForm('update', ${this.RawMaterialBatches[i].RMBId}, '${this.RawMaterialBatches[i].RMBName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
				data += `<td><button type="button" onclick="rawMatBatApp.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.RawMaterialBatches[i].RMBName}"
                data-id="${this.RawMaterialBatches[i].RMBId}" aria-label="Close">&times;</button></td>`;
				data += `</tr>`;
			}
		}
		console.log(this.RawMaterialBatches.length);
		this.Count(this.RawMaterialBatches.length);
		this.RMBEl.html(data);
	},


	<!--RenderInputField RAW MAT BATCH-->
	renderInputFields: function(id) {
		let rawMatBatch = this.RawMaterialBatches.filter((el) => el.RMBId === id)[0];
		if (rawMatBatch) {
			$("#RMBIdInput").val(rawMatBatch.RMBId);
			$("#rawMatInput").val(rawMatBatch.rawMatId);
			$("#amountInput").val(rawMatBatch.amount);
		}
	}
};