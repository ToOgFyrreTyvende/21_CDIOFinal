const rendersRawMat = {
	renderRawMaterials: function(RawMaterials) {
		let data = '';

		if (this.RawMaterials.length > 0) {
			for (i = 0; i < this.RawMaterials.length; i++) {
				data += `<tr id="user${this.RawMaterials[i].rawMatId}">`;
				data += `<td>${this.RawMaterials[i].rawMatID}</td>`;
				data += `<td>${this.RawMaterials[i].rawMatName}</td>`;

				data += `<td><button type="button" onclick="rawMatApp.setForm('update', ${this.RawMaterials[i].rawMatID}, '${this.RawMaterials[i].rawMatName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
				data += `<td><button type="button" onclick="rawMatApp.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.RawMaterials[i].rawMatName}"
                data-id="${this.RawMaterials[i].rawMatID}" aria-label="Close">&times;</button></td>`;
				data += `</tr>`;
			}
		}
		console.log(this.RawMaterials.length);
		this.Count(this.RawMaterials.length);
		this.RawMatEl.html(data);
	},

	<!--RenderInputField RAW MAT-->
	renderInputFields: function(id) {
		let rawMaterial = this.RawMaterials.filter((el) => el.rawMatID === id)[0];
		if (rawMaterial) {
			$("#rawMatIdInput").val(rawMaterial.rawMatID);
			$("#rawMatNameInput").val(rawMaterial.rawMatName);
		}
	}
};