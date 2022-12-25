angular.module('app',[]).controller('showController',function ($scope, $http){
    const contextPath = "http://localhost:8282/app/products";

    $scope.displayProduct = function () {

    if ($scope.minPrice == null){
    minPrice = 0
    }else{
    minPrice = $scope.minPrice
    }

     if ($scope.maxPrice == null){
        maxPrice = 1000
        }else{
        maxPrice = $scope.maxPrice
        }

    console.log(minPrice)
    console.log(maxPrice)

    console.log('display Product')
//        $http.get(contextPath)
//            .then(function(response) {
//                 $scope.ProductList = response.data;
//            });

$http({
        url: contextPath,
        method: 'GET',
        params:{
           minPrice: minPrice,
           maxPrice: maxPrice
           }
        }).then(function(response){
            $scope.ProductList = response.data;
            });
        }



      $scope.update = function(productId){
      console.log('update');
                $scope.displayProduct();
                     }

    $scope.deleteProduct = function(productId){
              $http.get(contextPath+ '/delete/' + productId)
                         .then(function(response) {
                             $scope.displayProduct();
                         });
                 }

      $scope.addProduct = function(){
      console.log("click add Product");
      console.log($scope.title);

        $http({
                url: contextPath +'/add',
                method: 'post',
                params:{
                    price:$scope.price,
                    title:$scope.title}
                }).then(function(response){
                    $scope.displayProduct()
                    });
                }



//  $scope.deleteProduct = function(productId){
//        $http({
//        url: contextPath +'/delete/',
//        method: 'GET',
//        params:{
//            productId: productId}
//        }).then(function(response){
//            $scope.displayProduct()
//            });
//        }

    $scope.displayProduct();
});
