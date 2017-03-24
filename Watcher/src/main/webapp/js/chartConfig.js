// JavaScript source code
/*
@ Developer: im jun hyeok
@ Date: 2016. 09. 23
@ project: watcher
*/

/*crimeChart*/
var crimeConfig = {
    backgroundColor: '#394264',
    type: "ring",
    plot: {
        slice: '50%',
        borderWidth: 0,
        backgroundColor: '#FBFCFE',
        animation: {
            effect: 2,
            sequence: 3
        }
    },
    plotarea: {
        backgroundColor: 'transparent',
        borderWidth: 0,
        borderRadius: "0 0 0 10",
        margin: "10 0 10 0"
    },
    scaleR: {
        refAngle: 270
    },
    tooltip: {
        visible: 'false'
    },
    series: [
        {
            text: "Docs",
            values: [48],
            lineColor: "#11A8AB",
            backgroundColor: "#11A8AB",
            lineWidth: 1,
        },
        {
            "value-box": {
                "visible": false
            },
            text: "Docs",
            values: [100],
            lineColor: "#394264",
            backgroundColor: "#394264",
            lineWidth: 1,
            marker: {
                backgroundColor: '#394264'
            }
        }
    ],
};

/*preCrimeChart*/
var preCrimeConfig = {
    backgroundColor: '#394264',
    type: "ring",

    plot: {
        slice: '50%',
        borderWidth: 0,
        backgroundColor: '#FBFCFE',
        animation: {
            effect: 2,
            sequence: 3
        }
    },
    plotarea: {
        backgroundColor: 'transparent',
        borderWidth: 0,
        borderRadius: "0 0 0 10",
        margin: "10 0 10 0"
    },
    scaleR: {
        refAngle: 270
    },
    tooltip: {
        visible: false
    },
    series: [
        {
            text: "Docs",
            values: [37],
            lineColor: "#3468AF",
            backgroundColor: "#3468AF",
            lineWidth: 1,
        },
        {
            "value-box": {
                "visible": false
            },
            text: "Docs",
            values: [63],
            lineColor: "#394264", 
            backgroundColor: "#394264",
            lineWidth: 1,
            marker: {
                backgroundColor: '#394264'
            }
        }
    ]
};

/*line chart*/
var trendCuvConfig = {
    type: "line",
    backgroundColor: "#3E414A",
    stacked: false,
    title: {
        text: "지역별 범죄 발생 추이",
        fontColor: "#EBEBEC",
        adjustLayout: true,
        marginTop: 15
    },
    subtitle: {
        text: "2015년 데이터 기준",
        fontColor: "#EBEBEC",
        adjustLayout: true,
        marginTop: 45
    },
    plot: {
        aspect: "spline",
        alphaArea: 0.6
    },
    plotarea: {
        margin: "dynamic"
    },
    tooltip: { visible: false },
    scaleY: {
        short: true,
        shortUnit: '%',
        lineColor: "#AAA5A5",
        tick: {
            lineColor: "#AAA5A5"
        },
        item: {
            fontColor: "#EBEBEC",
            paddingRight: 5
        },
        guide: {
            lineStyle: "dotted",
            lineColor: "#AAA5A5"
        },
        label: {
            text: "범죄예측율",
            fontColor: "#EBEBEC"
        }
    },
    scaleX: {
        lineColor: "#AAA5A5",
        labels: ["서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도"],
        tick: {
            lineColor: "#AAA5A5"
        },
        item: {
            fontColor: "#EBEBEC",
            paddingTop: 5
        },
    },
    crosshairX: {
        lineColor: "#AAA5A5",
        plotLabel: {
            backgroundColor: "#EBEBEC",
            borderColor: "#AAA5A5",
            borderWidth: 2,
            borderRadius: 2,
            thousandsSeparator: ','
        },
        scaleLabel: {
            backgroundColor: "#EBEBEC",
            borderColor: "#AAA5A5",
            fontColor: "#2A2B31"
        }
    },
    series: [
		{
		    values: [30, 40, 20, 10, 50 ,55, 23, 12, 19, 11, 9, 29, 0, 13, 15,16],
		    text: "절도",
		    backgroundColor: "#46C28C",
		    lineColor: "#46C28C",
		    marker: {
		        backgroundColor: "#46C28C",
		        borderColor: "#46C28C"

		    }
		},
		{
		    values: [8, 15, 7, 30, 40, 20, 50, 90, 88, 50, 60, 0, 50, 15, 16, 76],
		    text: "강간",
		    backgroundColor: "#D47362",
		    lineColor: "#D47362",
		    marker: {
		        backgroundColor: "#D47362",
		        borderColor: "#D47362"

		    }
		},
		{
		    values: [30, 40, 20, 10, 50, 55, 23, 12, 19, 11, 9, 29, 0, 50, 15, 16],
		    text: "강도",
		    backgroundColor: "#0CBADB",
		    lineColor: "#0CBADB",
		    marker: {
		        backgroundColor: "#0CBADB",
		        borderColor: "#0CBADB"

		    }
		},
        {
            values: [30, 40, 20, 10, 50, 55, 23, 12, 19, 11, 9, 29, 0,53, 23, 21],
            text: "살인",
            backgroundColor: "#F5D0A9",
            lineColor: "#F7BE81",
            marker: {
                backgroundColor: "#F5D0A9",
                borderColor: "#F7BE81"

            }
        },
        {
            values: [30, 40, 20, 10, 50, 55, 23, 12, 19, 11, 9, 29, 0,14 ,46, 42],
            text: "폭력",
            backgroundColor: "#D358F7",
            lineColor: "#CC2EFA",
            marker: {
                backgroundColor: "#D358F7",
                borderColor: "#CC2EFA"

            }
        }
    ]
};

