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
			type : 'post',
			url : 'listloading.do',
			data : {
				currentPage : currentPage,
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
				category : category,
				content : content.serialize().replace(/%/g, '%25')
			},
			dataType : 'text',
			success : function(result) {
				execute(result);
				if(content!=null){
					totalList();
				}
			}
		});
	}
	function list(arr) {
		var str = '';
		var birth = '';
		$.each(arr, function(i) {
			birth = arr[i].m_birth.split(' ')[0];
			str += '<tr>';
			str += "<td><a href='read.do?id=" + arr[i].m_id + "'>"
					+ arr[i].m_id + "</a></td>";
			str += '<td>' + arr[i].m_name + '</td>';
			str += '<td>' + birth + '</td>';
			str += '<td>' + arr[i].m_age + '</td>';
			str += '<td>' + arr[i].m_phone + '</td>';
			str += '<td>' + arr[i].m_email + '</td>';
			str += '<td>' + arr[i].m_nickname + '</td>';
			str += '<td>' + arr[i].m_grade + '</td>';
			str += '</tr>';
		});
		if (str != '') {
			$("#ajaxTable").html(str);
		} else {
			str += "<td colspan='10'>검색하신 정보는 없습니다.</td>";
			$("#ajaxTable").html(str);
		}
	}
	function page(arr) {
		var start = arr.beginPageNum;
		var end = arr.stopPageNum;
		var currentPage = arr.currentPage;
		var totalPage = arr.totalPage;
		var startNum = arr.startNum;
		var endNum = arr.endNum;
		var amount = arr.amount;
		var listSize = arr.listSize;
		var msg = "";
		if (listSize < 10 && endNum != amount) {
			end = (listSize / 10) + 1;
		}
		msg += "<table id='paginTable'>";
		msg += "<tr>";
		if (currentPage > 1) {
			msg += "<th>";
			if (currentPage > 10) {
				msg += "<strong onclick='getCurrentPage(" + 1
						+ ")' > <<   </strong>";
			}
			msg += "<strong onclick='getCurrentPage(" + (currentPage - 1)
					+ ")'> < </strong>";
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