$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
	 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateFundsForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidFundsIDSave").val() == "") ? "POST" : "PUT";

$.ajax( 
{ 
url : "fundsAPI", 
type : type, 
data : $("#formFunds").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onItemSaveComplete(response.responseText, status); 
} 
});

});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidFundsIDSave").val($(this).data("fundID"));
 $("#propID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#amount").val($(this).closest("tr").find('td:eq(1)').text());
 $("#description").val($(this).closest("tr").find('td:eq(2)').text());
});
//DELETE====================================================
$(document).on("click", ".btnRemove", function(event)
{ 

 $.ajax( 
 { 
 url : "fundsAPI", 
 type : "DELETE", 
 data : "fundID=" + $(this).data("fundID"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 

 }); 
});
// CLIENT-MODEL================================================================
function validateFundsForm()
{
// ID
if ($("#propID").val().trim() == "")
 {
 return "Insert Proposal ID.";
 }
// AMOUNT
if ($("#amount").val().trim() == "")
 {
 return "Insert Fund Amount.";
 } 
// DESCRIPTION-------------------------------
if ($("#description").val().trim() == "")
 {
 return "Insert Fund Desription.";
 }

 
}