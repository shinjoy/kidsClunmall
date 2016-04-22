<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		<div class="pagelist">
			<input type="hidden" name="currentPage" value="1">
			<button type="button" class="round gray" onclick="searchList(this.form, 1);">&nbsp;&lt;&lt;&nbsp;</button>

			<button type="button" class="round gray">&nbsp;&lt;&nbsp;</button>

					<button type="button" class="round white" style="font-weight:bold;">&nbsp;1&nbsp;</button>
					<!-- 
					<button type="button" class="round white" onclick="searchList(this.form, 2);">&nbsp;2&nbsp;</button>
					 -->

				<button type="button" class="round gray" onclick="searchList(this.form, 2);">&nbsp;&gt;&nbsp;</button>

			<button type="button" class="round gray" onclick="searchList(this.form, 2);">&nbsp;&gt;&gt;&nbsp;</button>
			<div class="clear"></div>
		</div>
