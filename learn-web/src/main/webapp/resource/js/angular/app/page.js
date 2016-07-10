(function(angular)
{
	 angular.module("myPage",[])
	        .controller("MyPageController",[$scope,$window,function($scope,$window){
	        	
	        	$scope.page.page1=1;
	        	$scope.page.page2=2;
	        	$scope.page.page3=3;
	        	$scope.page.page4=4;
	        	$scope.page.page5=5;
	        	$scope.page.pre = function() {
	        		
	        		$scope.page.pageIndex = $scope.page.pageIndex-1;
	        		
	        		if($scope.page.pageIndex<1) $scope.page.pageIndex= 1;
	        		
	        		setPage();
	        	}
                $scope.page.next = function() {
	        		
	        		$scope.page.pageIndex = $scope.page.pageIndex+1;
	        		
	        		if($scope.page.pageIndex<1) $scope.page.pageIndex= 1;
	        		
	        		setPage();
	        	}
	        	function setPage()
	        	{
	        		$scope.page.page1=($scope.page.pageIndex-1)+1;
	        		$scope.page.page2=($scope.page.pageIndex-1)+2;
	        		$scope.page.page3=($scope.page.pageIndex-1)+3;
	        		$scope.page.page4=($scope.page.pageIndex-1)+4;
	        		$scope.page.page5=($scope.page.pageIndex-1)+5;
	        	}
	        	
	        	function query(pageIndex)
	        	{
	        		if($window.page.pageIndex) $window.page.pageIndex = pageIndex;
	        		
	        		if($window.query)$window.query();
	        			
	        	}
	        	
	         }])
	        .directive("myPage",function(){
	        	return {
	        		 restrict:"E"
	        	    ,replace:true
	        	    ,template:"<nav> "+
							  "  <ul class='pagination'> "+
							  "    <li> "+
							  "       <a href='#' aria-label='Previous'> "+
							  "        <span aria-hidden='true'>&laquo;</span> "+
							  "      </a> "+
							  "    </li> "+
							  "	   <li><a href='#' ng-click='page.pre()'>{{page.page1}}</a></li> "+
							  "    <li><a href='#'>{{page.page2}}</a></li> "+
							  "    <li><a href='#'>{{page.page3}}</a></li> "+
							  "    <li><a href='#'>{{page.page4}}</a></li> "+
							  "    <li><a href='#'>{{page.page5}}</a></li> "+
							  "	    <li> "+
							  "	      <a href='#' aria-label='Next' ng-click='page.next()'> "+
							  "	        <span aria-hidden='true'>&raquo;</span> "+
							  "	      </a> "+
							  "	    </li> "+
							  "	  </ul> "+
							  "	</nav>"
	        		
	        	}
	        	
	        });
})(window.angular)