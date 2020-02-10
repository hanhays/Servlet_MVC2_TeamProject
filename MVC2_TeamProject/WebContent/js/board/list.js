	var category = null;
	var content = null;
	var currentPage = null;
	if (currentPage == null) {
		currentPage = 1;
	}
	$(function() {
		firstList();
		$("#totalList").click(function(){
			$("input[name='content']").val("");
			content=null;
			$("#ajaxTable").html(" ");
			$("#paging").html(" ");
			$("#totalList").html(" ");
			currentPage=1;
			firstList();  
		});
		$("#searchList").click(function(){
			currentPage=1;
			searchList();
		});
	});
	function firstList(){
		
		$.ajax({ 
			type : 'get',
			url : 'listloading.do',
			data : {
				currentPage : currentPage,
				flag:$("#getFlag").text()
			},
			dataType : 'text', 
			success : function(result) {
				execute(result);
			}
		});
	}
	function searchList() {
		category = $("select[name='category']").val();
		content = $("input[name='content']");
		
		$.ajax({
			type : 'post',
			url : 'search.do',
			data : {
				currentPage : currentPage,
				category 	: category,
				content		: content.serialize().replace(/%/g, '%25'),
				flag:$("#getFlag").text()
			},
			dataType : 'text',
			success : function(result) {
				console.log(result);
				execute(result);
				if(content!=null){
					totalList();
				}
			}
		});
	}
	function list(arr) {
		var str = '';
		var writtenday = '';
		$.each(arr, function(i) {
			writtenday = arr[i].b_day.split(' ')[0];
			str += '<tr>';
			str += '<td>' + arr[i].b_num + '</td>';
			str += "<td><a href='read.do?b_num=" + arr[i].b_num + "'>"
					+ arr[i].b_title + "</a></td>";
			str += '<td>' + arr[i].m_id + '</td>';
			str += '<td>' + writtenday + '</td>';
			str += '<td>' + arr[i].b_cnt + '</td>';
			str += '</tr>';
		});
		if (str != '') {
			$("#ajaxTable").html(str);
		} else {
			str += "<td colspan='10'>검색하신 정보가 유효하지 않습니다.</td>";
			$("#ajaxTable").html(str);
		}
	}
	function page(arr) {
		var amount = arr.amount;
		var totalPage = arr.totalPage;
		var currentPage = arr.currentPage;
		var start = arr.beginPageNum;
		var end = arr.stopPageNum;
		var startNum = arr.startNum;
		var endNum = arr.endNum;
		var msg = "";
		msg += "<table id='paginTable'>";
		msg += "<tr>";
		if (currentPage > 1) {
			msg += "<th>";
			if (currentPage > 10) {
				msg += "<strong onclick='getCurrentPage(" +1+ ")' > <<   </strong>";
			}
			msg += "<strong onclick='getCurrentPage(" +(currentPage - 1)+ ")'> < </strong>";
			msg += "</th>";
		}
		for (i = start; i <= end; i++) {
			msg += "<th>";
			if (i == currentPage) {
				msg += "<strong onclick='getCurrentPage("+i+")' style='color:red;'>"+i+"</strong>";
			} else {
				msg += "<strong onclick='getCurrentPage("+i+")' style='color:black;'>"+i+"</strong>";
			}
			msg += "</th>";

		}
		if (currentPage<totalPage&&end>2) {
			msg += "<th><strong onclick='getCurrentPage("+ (currentPage + 1)+")'> > </strong>";
			msg += "<strong onclick='getCurrentPage("+totalPage+")'> >> </strong></th>";
		}
		msg += "</tr>";
		msg += "</table>";
		$("#paging").html(" ");
		$("#paging").append(msg);
	} 
	function getCurrentPage(currentPages) {
		currentPage = currentPages;
		if(content!=null){
			searchList();
		}else{
			firstList();
		}
	}
	function totalList(){
		var msg="";
		msg += "<strong>";
		msg += "전체 List";
		msg += "</strong>";
		$("#totalList").html(" ");
		$("#totalList").append(msg);
	}
	function execute(result){
		var arr = JSON.parse(result);
		if (arr != null) {
			list(arr[0]);
			page(arr[1][0]);
		}
	}