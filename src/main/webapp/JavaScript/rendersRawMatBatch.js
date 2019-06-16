const renders = {
    renderRawMaterialBatches: function (RawMaterialBatches) {
        let data = '';

        if (this.RawMaterialBatches.length > 0) {
            for (i = 0; i < this.RawMaterialBatches.length; i++) {
                data += `<tr id="user${this.RawMaterialBatches[i].RMBId}">`;
                data += `<td>${this.RawMaterialBatches[i].RMBId}</td>`;
                data += `<td>${this.RawMaterialBatches[i].rawMatId}</td>`;
                data += `<td>${this.RawMaterialBatches[i].amount}</td>`;

                data += `<td><button type="button" onclick="app.setForm('update', ${this.RawMaterialBatches[i].RMBId}, '${this.RawMaterialBatches[i].RMBName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
                data += `<td><button type="button" onclick="app.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.RawMaterialBatches[i].RMBName}"
                data-id="${this.RawMaterialBatches[i].RMBId}" aria-label="Close">&times;</button></td>`;
                data += `</tr>`;
            }
        }
        console.log(this.RawMaterialBatches.length)
        this.Count(this.RawMaterialBatches.length);
        this.RMBEl.html(data);
    },


    <!--RenderInputField RAW MAT BATCH-->
    renderInputFields: function(id){
        RawMaterialBatch = this.RawMaterialBatches.filter((el) => el.RMBId === id)[0]
        if(RawMaterialBatch){
            $("#RMBIdInput").val(RawMaterialBatch.RMBId);
            $("#rawMatInput").val(RawMaterialBatch.rawMatId);
            $("#amountInput").val(RawMaterialBatch.amount);
        }
    }
}