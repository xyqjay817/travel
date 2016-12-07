require([],
    function() {
        console.log($('#p').text())
               $(document).ready(function () {
           $("#grid").kendoGrid({
               dataSource: {
                   type: "odata",
                   transport: {
                       read: "//demos.telerik.com/kendo-ui/service/Northwind.svc/Customers"
                   },
                   pageSize: 20
               },
               height: 550,
               groupable: true,
               sortable: true,
               pageable: {
                   refresh: true,
                   pageSizes: true,
                   buttonCount: 5
               },
               columns: [{
                   template: "<div class='customer-photo'" +
                   "style='background-image: url(../content/web/Customers/#:data.CustomerID#.jpg);'></div>" +
                   "<div class='customer-name'>#: ContactName #</div>",
                   field: "ContactName",
                   title: "Contact Name",
                   width: 240
               }, {
                   field: "ContactTitle",
                   title: "Contact Title"
               }, {
                   field: "CompanyName",
                   title: "Company Name"
               }, {
                   field: "Country",
                   width: 150
               }]
           });
       });
        // 初始化,通常是在业务页面代码的最后执行
        // $.init();
    }
)