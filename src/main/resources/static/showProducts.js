angular.module('app',[]).controller('showController',function ($scope, $http){
    const contextPath = "http://localhost:8282/app/api/v1/products";
    var page = 1;

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
           page: page
           }
        }).then(function(response){
        console.log(response.data.content);
            $scope.ProductList = response.data.content;
            });
        }

      $scope.previousPage = function(){
      page = page -1;
      if (page < 1){
      page = 1;
      }
      console.log("page="+page);
      $scope.displayProduct();
      }

      $scope.nextPage = function(){
      page = page +1;
      console.log(page);
      $scope.displayProduct();
      }

      $scope.update = function(productId){
      console.log('update');
                $scope.displayProduct();
                     }

    $scope.deleteProduct = function(productId){
              $http.delete(contextPath+ '/' + productId)
                         .then(function(response) {
                             $scope.displayProduct();
                         });
                 }

      $scope.addProduct = function(){
      console.log("click add Product");
      console.log($scope.title);

          $http.post(contextPath, $scope.newProduct)
          .then(function(response){
           $scope.displayProduct()
            });
           };
//        $http({
//                url: contextPath,
//                method: 'post',
//                params:{
//                    price:$scope.price,
//                    title:$scope.title}
//                }).then(function(response){
//                    $scope.displayProduct()
//                    });
//                }

    $scope.displayProduct();
});
