<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:template match="/">
		<html>

			<head>
				<style type="text/css">
					table.tfmt {
					border: 1px ;
					}

					td.colfmt {
					border: 1px ;
					background-color: white;
					color: black;
					text-align:right;
					}

					th {
					background-color: #ad5202;
					color: white;
					}

				</style>
			</head>

			<body>
<!-- /////////////////////    DEPOSITS     //////////////////////////////// -->
<table class="tfmt">
	<th style="width:10000px"></th>
</table>
				<table class="tfmt">
					<th style="width:10000px">Deposits</th>
				</table>
				<table class="tfmt">
					<th style="width:10000px"></th>
				</table>
				<table class="tfmt">
					<tr>
						<th style="width:250px">Deposit Code:</th>
						<th style="width:350px">Description:</th>
          </tr>
					<xsl:for-each select="factory/deposits/deposit">
						<tr>
							<td class="colfmt">
								<xsl:value-of select="code" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="description" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
<!-- //////////////////////     MACHINES    //////////////////////////////// -->
<table class="tfmt">
	<th style="width:10000px"></th>
</table>
				<table class="tfmt">
					<th style="width:10000px">Machines</th>
				</table>
				<table class="tfmt">
					<th style="width:10000px"></th>
				</table>
				<table class="tfmt">
					<tr>
						<th style="width:250px">Machine Code:</th>
						<th style="width:350px">Brand:</th>
						<th style="width:350px">Model:</th>
						<th style="width:350px">Install Date:</th>
						<th style="width:350px">Description:</th>
						<th style="width:350px">Serial Number:</th>
						<th style="width:350px">Production Line:</th>
						<th style="width:350px">Position:</th>
	        </tr>
					<xsl:for-each select="factory/machines/machine">
						<tr>
							<td class="colfmt">
								<xsl:value-of select="code" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="brand" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="model" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="installDate" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="description" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="serialNumber" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="machinePosition/productionLine" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="machinePosition/position" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
<!-- ///////////////////////     PRODUCTS   //////////////////////////////// -->
<table class="tfmt">
	<th style="width:10000px"></th>
</table>
<table class="tfmt">
	<th style="width:10000px">Products</th>
</table>
<table class="tfmt">
	<th style="width:10000px"></th>
</table>
<table class="tfmt">
	<tr>
		<th style="width:250px">Product Code:</th>
		<th style="width:350px">Commercial Code:</th>
		<th style="width:350px">Brief Description:</th>
		<th style="width:350px">Full Description:</th>
		<th style="width:350px">Category:</th>
		<th style="width:350px">Measure Unit:</th>
		<th style="width:350px">Has Bill of Materials:</th>
		<th style="width:350px">Bill of Materials ID:</th>
		<th style="width:350px">Item Identity:</th>
		<th style="width:350px">Quantity:</th>
		<th style="width:350px">Item Measure Unit:</th>
	</tr>
	<xsl:for-each select="factory/products/product">
		<tr>
			<td class="colfmt">
				<xsl:value-of select="code" />
			</td>
			<td class="colfmt">
				<xsl:value-of select="commercialCode" />
			</td>
			<td class="colfmt">
				<xsl:value-of select="briefDescription" />
			</td>
			<td class="colfmt">
				<xsl:value-of select="fullDescription" />
			</td>
			<td class="colfmt">
				<xsl:value-of select="category" />
			</td>
			<td class="colfmt">
				<xsl:value-of select="measureUnit" />
			</td>
			<td class="colfmt">
				<xsl:value-of select="existenceOfBOM" />
			</td>
			<td class="colfmt">
				<xsl:value-of select="billOfMaterials/identity" />
			</td>
			<td class="colfmt">
				<xsl:value-of select="billOfMaterials/id" />
			</td>
			<td class="colfmt">
				<xsl:value-of select="billOfMaterials/quantity" />
			</td>
			<td class="colfmt">
				<xsl:value-of select="billOfMaterials/measureUnit" />
			</td>
		</tr>
	</xsl:for-each>
</table>
<!-- //////////////////      RAW MATERIALS    ////////////////////////////// -->
<table class="tfmt">
	<th style="width:10000px"></th>
</table>
				<table class="tfmt">
					<th style="width:10000px">Raw Materials</th>
				</table>
				<table class="tfmt">
					<th style="width:10000px"></th>
				</table>
				<table class="tfmt">
					<tr>
						<th style="width:250px">Raw Material Code:</th>
						<th style="width:350px">Technical Sheet:</th>
						<th style="width:350px">Description:</th>
						<th style="width:350px">Category ID:</th>
						<th style="width:350px">Category Description:</th>
          </tr>
					<xsl:for-each select="factory/rawMaterials/rawMaterial">
						<tr>
							<td class="colfmt">
								<xsl:value-of select="code" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="technicalSheet" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="description" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="category/id" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="category/categoryDescription" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
<!-- //////////////////   PRODUCTION ORDERS   ////////////////////////////// -->
<table class="tfmt">
	<th style="width:10000px"></th>
</table>
				<table class="tfmt">
					<th style="width:10000px">Production Orders</th>
				</table>
				<table class="tfmt">
					<th style="width:10000px"></th>
				</table>
				<table class="tfmt">
					<tr>
						<th style="width:250px">Production Order Code:</th>
						<th style="width:350px">Emission Date:</th>
						<th style="width:350px">Expected Finish Date:</th>
						<th style="width:350px">Product Code:</th>
						<th style="width:350px">Quantity:</th>
						<th style="width:350px">Measure Unit:</th>
						<th style="width:350px">Orders:</th>
          </tr>
					<xsl:for-each select="factory/prodOrders/ProductionOrder">
						<tr>
							<td class="colfmt">
								<xsl:value-of select="code" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="EmissionDate" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="ExpectedDate" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="ProductCode" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="Quantity" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="Unit" />
							</td>
							<td class="colfmt">
								<xsl:value-of select="ReqCodes" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
<!-- /////////////////////////////////////////////////////////////////////// -->
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
