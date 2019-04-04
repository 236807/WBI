function [] = drawScoreMatrix(scoreMatrix)
figure(1)
heatmap(scoreMatrix);
title('Score Matrix');
xlabel('1-sequence');
ylabel('2-sequence');
saveas(gcf,'plot.png');
end