'<template>
	<div id="main4" style="width: 100%; height: 100%;"></div>
</template>
<script type="text/javascript">
	import * as echarts from 'echarts';
	export default {
		mounted: () => {
		var chartDom = document.getElementById('main4');
		var myChart = echarts.init(chartDom);
		var option;
		
		option = {
		  legend: {
		    data: ['Altitude (km) vs. temperature (°C)']
		  },
		  tooltip: {
		    trigger: 'axis',
		    formatter: 'Temperature : <br/>{b}km : {c}°C'
		  },
		  grid: {
		    left: '3%',
		    right: '4%',
		    bottom: '3%',
		    containLabel: true
		  },
		  xAxis: {
		    type: 'value',
		    axisLabel: {
		      formatter: '{value} °C'
		    }
		  },
		  yAxis: {
		    type: 'category',
		    axisLine: { onZero: false },
		    axisLabel: {
		      formatter: '{value} km'
		    },
		    boundaryGap: false,
		    data: ['0', '10', '20', '30', '40', '50', '60', '70', '80']
		  },
		  series: [
		    {
		      name: 'Altitude (km) vs. temperature (°C)',
		      type: 'line',
		      symbolSize: 10,
		      symbol: 'circle',
		      smooth: true,
		      lineStyle: {
		        width: 3,
		        shadowColor: 'rgba(0,0,0,0.3)',
		        shadowBlur: 10,
		        shadowOffsetY: 8
		      },
		      data: [15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5]
		    }
		  ]
		};
		
		option && myChart.setOption(option);
	},
	}
</script>
