<#macro page urlPrefix urlPostfix pageInfo>
	<#if pageInfo.pageCount &gt; 1 >
        <nav class="text-center">
            <ul class="pagination pagination-lg">
			<#assign pageCount=pageInfo.pageCount pageIndex=pageInfo.pageIndex liststep=4>
			<#assign bar=liststep/2 listend=pageIndex+bar>
			<#if pageIndex-bar &lt; 1>
				<#assign listbegin=1/>
			<#else>
				<#assign listbegin=pageIndex-bar/>
			</#if>
		 	<#if listend &gt; pageCount>
		 		<#assign listend=pageCount/>
		 	</#if>

			<#if pageIndex &gt; 1>
                <li><a href="${urlPrefix}${pageInfo.getPrevious()}${urlPostfix!''}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
			</#if>
			<#if listbegin &gt; 1>
                <li><a href="${urlPrefix}1${urlPostfix!''}">1</a></li>
			</#if>
			<#if listbegin &gt; 2>
                <li><a style="border: 0px none;">...</a></li>
			</#if>
			<#list listbegin .. listend as i>
				<#if i!=pageIndex>
                    <li><a href="${urlPrefix}${i}${urlPostfix!''}" title="page ${i}">${i}</a></li>
				<#elseif listend &gt;= 1>
                    <li><a class="current-page">${i}</a></li>
				</#if>
			</#list>
	 		<#if listend &lt; pageCount>
	 			<#if listend+1 &lt; pageCount>
                    <li><a style="border: 0px none;">...</a></li>
	 			</#if>
                <li><a href="${urlPrefix}${pageCount}${urlPostfix!''}" title="page ${pageCount}">${pageCount}</a></li>
			</#if>
			<#if pageIndex!=pageCount>
                <li><a href="${urlPrefix}${pageInfo.getNext()}${urlPostfix!''}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			</#if>
            </ul>
        </nav>
	</#if>
</#macro>