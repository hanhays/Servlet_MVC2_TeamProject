	$(function(){
		$("#ajax").click(function(/* event */) {
							//event.preventDefault(); 	
							var category = $("select[name='category']").val();
							var content = $("input[name='content']");
							console.log(currentPage);
							 if(currentPage == null){
								currentPage = 1;	
							} 
							console.log(currentPage);
							$.ajax({
									type : 'get',
									url : 'search.do',
									data : {
											currentPage : currentPage,
											category : category,
											content :content.serialize().replace(/%/g,'%25')
											
										},
								dataType : 'text',
								success : function(result) { 
											var arr = JSON.parse(result);
											console.log(arr);
											var str = '';
											var birth ='';
											$.each(arr[0],function(i) {
													birth = arr[0][i].m_birth.split(' ')[0];
													str += '<tr>';
													str += "<td><a href='read.do?id="+ arr[0][i].m_id+ "'>"+ arr[0][i].m_id+ "</a></td>";
													str += '<td>'+ arr[0][i].m_name+ '</td>';
													str += '<td>'+ birth+ '</td>';
													str += '<td>'
															+ arr[0][i].m_age
															+ '</td>';
													str += '<td>'
															+ arr[0][i].m_phone
															+ '</td>';
													str += '<td>'
															+ arr[0][i].m_email
															+ '</td>';
													str += '<td>'
															+ arr[0][i].m_nickname
															+ '</td>';
													str += '<td>'
															+ arr[0][i].m_grade
															+ '</td>';
													str += '</tr>';
															});
											if (str != '') {
												$("#ajaxTable").html(str);
											} else {
												str += "<td colspan='10'>조회하신 정보가 유효하지 않습니다.</td>";
												$("#ajaxTable").html(str);
											}
											var start = arr[1][0].beginPageNum;
											var end = arr[1][0].stopPageNum;
											var currentPage = arr[1][0].currentPage;
											var totalPage = arr[1][0].totalPage;
											var startNum = arr[1][0].startNum;
											var endNum = arr[1][0].endNum;
											var amount = arr[1][0].amount;
											$("#paging").html(" ");
											for(i=start; i<=end; i++){
												if(i==currentPage){
												$("#paging").append("<button onclick='getCurrentPage("+i+")' style='color:red;'>"+i+"</button>");
												}else{
													$("#paging").append("<button onclick='getCurrentPage("+i+")' style='color:black;'>"+i+"</button>");
												}
											}
										}
									});
						});
	});