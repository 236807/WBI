function [] = drawScoreMatrix(scoreMatrix,road)
figure(1)
[row,col]=find(isnan(road));
cdata=[row,col];
for x = 1:length(cdata)
    scoreMatrix(cdata(x,1),cdata(x,2)) = NaN;
end
h=heatmap(scoreMatrix);
h.Title='Score Matrix';
h.XLabel = '1-sequence';
h.YLabel = '2-sequence';
h.ColorbarVisible = 'off';
saveas(gcf,'plot.png');
end