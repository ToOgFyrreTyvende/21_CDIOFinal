const renders = {
    renderProductBatches: function (ProductBatches) {
        let data = '';

        if (this.ProductBatches.length > 0) {
            for (i = 0; i < this.ProductBatches.length; i++) {
                data += `<tr id="user${this.ProductBatches[i].productBatchId}">`;
                data += `<td>${this.ProductBatches[i].productBatchId}</td>`;
                data += `<td>${this.ProductBatches[i].receptId}</td>`;
                data += `<td>${this.ProductBatches[i].status}</td>`;
                data += `<td>${this.ProductBatches[i].userId}</td>`;
                data += `<td>${this.ProductBatches[i].RMBId}</td>`;
                data += `<td>${this.ProductBatches[i].tara}</td>`;
                data += `<td>${this.ProductBatches[i].netto}</td>`;

                data += `<td><button type="button" onclick="app.setForm('update', ${this.ProductBatches[i].productBatchId}, '${this.ProductBatches[i].productBatchName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
                data += `<td><button type="button" onclick="app.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.ProductBatches[i].productBatchName}"
                data-id="${this.ProductBatches[i].productBatchId}" aria-label="Close">&times;</button></td>`;
                data += `</tr>`;
            }
        }
        console.log(this.ProductBatches.length)
        this.Count(this.ProductBatches.length);
        this.RroductBatchEl.html(data);
    },

    <!--RenderInputField PRODUKTBATCH-->
    renderInputFields: function(id){
        ProductBatch = this.ProductBatches.filter((el) => el.productBatchId === id)[0]
        if(ProductBatch){
            $("#productBatchIdInput").val(ProductBatch.productBatchId);
            $("#receptIdInput").val(ProductBatch.receptId);
            $("#statusInput").val(ProductBatch.status);
            $("#userIDInput").val(ProductBatch.userId);
            $("#RMBIdInput").val(ProductBatch.RMBId);
            $("#taraInput").val(ProductBatch.tara);
            $("#nettoInput").val(ProductBatch.netto);
        }
    }
}