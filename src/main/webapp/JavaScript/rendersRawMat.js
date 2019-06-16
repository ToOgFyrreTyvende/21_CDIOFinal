const rendersRawMat = {
    renderRawMaterials: function (RawMaterials) {
        let data = '';

        if (this.RawMaterials.length > 0) {
            for (i = 0; i < this.RawMaterials.length; i++) {
                data += `<tr id="user${this.RawMaterials[i].rawMatId}">`;
                data += `<td>${this.RawMaterials[i].rawMatId}</td>`;
                data += `<td>${this.RawMaterials[i].rawMatName}</td>`;
                data += `<td>${this.RawMaterials[i].supplier}</td>`;

                data += `<td><button type="button" onclick="RawMatApp.setForm('update', ${this.RawMaterials[i].rawMatId}, '${this.RawMaterials[i].rawMatName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
                data += `<td><button type="button" onclick="RawMatApp.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.RawMaterials[i].rawMatName}"
                data-id="${this.RawMaterials[i].rawMatId}" aria-label="Close">&times;</button></td>`;
                data += `</tr>`;
            }
        }
        console.log(this.RawMaterials.length)
        this.Count(this.RawMaterials.length);
        this.RawMatEl.html(data);
    },

    <!--RenderInputField RAW MAT-->
    renderInputFields: function(id){
        RawMaterial = this.RawMaterials.filter((el) => el.rawMatId === id)[0]
        if(RawMaterial){
            $("#rawMatInput").val(RawMaterial.rawMatId);
            $("#rawMatNameInput").val(RawMaterial.rawMatName);
            $("#supplierInput").val(RawMaterial.supplier);
        }
    }
}