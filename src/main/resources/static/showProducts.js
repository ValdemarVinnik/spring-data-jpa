angular.module('app',[]).controller('showController',function ($scope, $http){
    const contextPath = "http://localhost:8282/app/products";
    var step = 0;

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

        $http({
        url: contextPath,
        method: 'GET',
        params:{
           minPrice: minPrice,
           maxPrice: maxPrice,
           step: step
           }
        }).then(function(response){
            $scope.ProductList = response.data;
            });
        }

      $scope.previousPage = function(){
      step = -10;
      console.log(step);
      $scope.displayProduct();
      }

      $scope.nextPage = function(){
      step = 10;
      console.log(step);
      $scope.displayProduct();
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

    $scope.displayProduct();
});
